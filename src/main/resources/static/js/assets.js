// assets.js

import { fetchAssets } from "./api.js";
import { showToast } from "./ui.js";
import {
    markAssetBroken,
    sendAssetToMaintenance,
    removeAssetFromRoom,
    assignAssetToRoom
} from "./api.js";
import { loadRooms } from "./rooms.js";


export async function getAssetsData() {
    try {
        return await fetchAssets();
    } catch (err) {
        console.error(err);
        return [];
    }
}

export async function loadAssets() {
    try {
        const assets = await fetchAssets();


        renderStats(assets);
        const rooms = await loadRooms();

        const container = document.getElementById("assets-container");
        if (container) {
            renderAssets(container, assets, rooms);
        }

        return assets;

    } catch (err) {
        console.error(err);
        showToast("Failed to load assets ❌", true);
        return [];
    }
}

function renderStats(assets) {
    const totalEl = document.getElementById("total-assets");
    const inUseEl = document.getElementById("in-use-assets");

    const total = assets.length;
    const inUse = assets.filter(a => a.status === "IN_USE").length;

    if (totalEl) totalEl.textContent = total;
    if (inUseEl) inUseEl.textContent = inUse;
}

function getStatusClass(status) {
    switch (status) {
        case "IN_USE": return "bg-green-50 text-green-700 border border-green-200";
        case "AVAILABLE": return "bg-blue-50 text-blue-700 border border-blue-200";
        case "MAINTENANCE": return "bg-yellow-50 text-yellow-700 border border-yellow-200";
        case "BROKEN": return "bg-red-50 text-red-700 border border-red-200";
        default: return "bg-gray-50 text-gray-600 border border-gray-200";
    }
}


function renderAssets(container, assets, rooms) {
    container.innerHTML = "";

    if (!assets.length) {
        container.innerHTML = `<p class="text-gray-400">No assets</p>`;
        return;
    }

    assets.forEach(asset => {
        const card = document.createElement("div");
        const room = rooms.find(r => r.roomId === asset.roomId);

        card.className = `
            p-6 rounded-2xl bg-white shadow-sm hover:shadow-lg border border-gray-100 transition
        `;

        // --- ACTIONS ---
        let actions = "";

        if (asset.status !== "BROKEN") {
            actions += `
                <button data-action="broken" data-id="${asset.id}"
                    class="text-xs px-3 py-1 bg-red-100 text-red-700 rounded">
                    Broken
                </button>
            `;
        }

        if (asset.status === "BROKEN") {
            actions += `
                <button data-action="maintenance" data-id="${asset.id}"
                    class="text-xs px-3 py-1 bg-yellow-100 text-yellow-700 rounded">
                    Maintenance
                </button>
            `;
        }


if (asset.roomId) {
    actions += `
        <button data-action="remove-room" data-id="${asset.id}"
            class="text-xs px-3 py-1 bg-gray-100 text-gray-700 rounded">
            Unassign
        </button>
    `;
} else {
    actions += `
        <button data-action="assign-room" data-id="${asset.id}"
            class="text-xs px-3 py-1 bg-blue-100 text-blue-700 rounded">
            Assign to room
        </button>
    `;
}

        card.innerHTML = `
            <div class="flex justify-between mb-3">

<div class="text-[11px] text-gray-500 mb-1 uppercase tracking-wide">
    Asset Status
</div>

<span class="${getStatusClass(asset.status)} px-2 py-0.5 text-[11px] font-semibold rounded-md inline-block">
    ${asset.status.replace("_", " ")}
</span>

            </div>

            <h4 class="font-bold">${asset.assetType}</h4>

            <p class="text-xs text-gray-500 mb-2">
                SN: ${asset.serialNumber || "N/A"}
            </p>
            <p class="text-xs text-gray-400">
                Room: ${room ? room.number : "—"}
            </p>

            <div class="mt-4 flex gap-2 flex-wrap">
                ${actions}
            </div>
        `;

        container.appendChild(card);
    });

    // BROKEN
    container.querySelectorAll('[data-action="broken"]').forEach(btn => {
        btn.onclick = async () => {
            try {
                await markAssetBroken(btn.dataset.id);
                showToast("Marked as broken 🔧");
                await loadAssets();
            } catch (err) {
                console.error(err);
                showToast("Failed ❌", true);
            }
        };
    });

    // MAINTENANCE
    container.querySelectorAll('[data-action="maintenance"]').forEach(btn => {
        btn.onclick = async () => {
            try {
                await sendAssetToMaintenance(btn.dataset.id);
                showToast("Sent to maintenance 🛠️");
                await loadAssets();
            } catch (err) {
                console.error(err);
                showToast("Failed ❌", true);
            }
        };
    });

    // REMOVE
    container.querySelectorAll('[data-action="remove-room"]').forEach(btn => {
        btn.onclick = async () => {
            try {
                await removeAssetFromRoom(btn.dataset.id);
                showToast("Removed from room 📦");
                await loadAssets();
            } catch (err) {
                console.error(err);
                showToast("Failed ❌", true);
            }
        };
    });


    // ASSIGN
    container.querySelectorAll('[data-action="assign-room"]').forEach(btn => {
        btn.onclick = async () => {
            try {
                const roomId = prompt("Enter room ID:");

                if (!roomId) return;

                await assignAssetToRoom(btn.dataset.id, roomId);

                showToast("Assigned to room 🏫");

                await loadAssets();

            } catch (err) {
                console.error(err);
                showToast("Assign failed ❌", true);
            }
        };
    });


}