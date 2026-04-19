// assets.js

import { fetchAssets } from "./api.js";
import { showToast } from "./ui.js";
import {
    markAssetBroken,
    sendAssetToMaintenance,
    removeAssetFromRoom,
    assignAssetToRoom,
    markAssetAvailable
} from "./api.js";
import { loadRooms } from "./rooms.js";

let selectedAssetId = null;

const baseBtnClass = `
    px-3 py-1 text-xs rounded
    bg-orange-500/10 text-orange-300
    hover:bg-orange-500 hover:text-black
    transition
`;

export async function getAssetsData() {
    try {
        return await fetchAssets();
    } catch (err) {
        console.error(err);
        return [];
    }
}

export async function loadAssets(filters = {}) {
    try {
        const assets = await fetchAssets(filters);

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
    const maintenanceEl = document.getElementById("maintenance-assets");

    const total = assets.length;
    const inUse = assets.filter(a => a.status === "IN_USE").length;
    const maintenance = assets.filter(a => a.status === "MAINTENANCE").length;

    if (totalEl) totalEl.textContent = total;
    if (inUseEl) inUseEl.textContent = inUse;
    if (maintenanceEl) maintenanceEl.textContent = maintenance;
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

        const statusStyle = `
            px-3 py-1 text-xs font-semibold rounded-full
            bg-orange-500/10 text-orange-300 border border-orange-500/20
        `;

        const userBadge = asset.status === "IN_USE"
            ? `
            <div class="flex items-center gap-2 text-xs text-gray-400">
                <div class="w-6 h-6 rounded-full bg-orange-500/20 flex items-center justify-center text-[10px]">
                    U
                </div>
                <span>Assigned user</span>
            </div>
        `
            : "";

        // --- ACTIONS ---
        let actions = "";

        if (asset.status === "AVAILABLE") {
            actions += `
                <button data-action="assign-room" data-id="${asset.id}" class="${baseBtnClass}">
                    Assign
                </button>
                <button data-action="maintenance" data-id="${asset.id}" class="${baseBtnClass}">
                    Maintenance
                </button>
            `;
        }

        if (asset.status === "IN_USE") {
            actions += `
                <button data-action="assign-room" data-id="${asset.id}" class="${baseBtnClass}">
                    Change Room
                </button>
                <button data-action="remove-room" data-id="${asset.id}" class="${baseBtnClass}">
                    Unassign
                </button>
                <button data-action="maintenance" data-id="${asset.id}" class="${baseBtnClass}">
                    Maintenance
                </button>
            `;
        }

        if (asset.status === "MAINTENANCE") {
            actions += `
                <button data-action="available" data-id="${asset.id}" class="${baseBtnClass}">
                    Available
                </button>
                <button data-action="broken" data-id="${asset.id}" class="${baseBtnClass}">
                    Broken
                </button>
            `;
        }

        card.className = `
            p-6 rounded-2xl
            bg-gradient-to-br from-[#111827] to-[#1f2937]
            border border-white/5
            hover:border-orange-500/30
            hover:shadow-[0_0_30px_rgba(249,115,22,0.15)]
            transition-all duration-300
            space-y-4
        `;

        card.innerHTML = `
            <div class="flex justify-between items-start">
                <span class="${statusStyle}">
                    ${asset.status.replace("_", " ")}
                </span>

                <span class="material-symbols-outlined text-gray-500">
                    inventory_2
                </span>
            </div>

            <div>
                <h3 class="text-lg font-bold">${asset.assetType}</h3>
                <p class="text-xs text-gray-400 mt-1">
                    SN: ${asset.serialNumber || "N/A"}
                </p>
            </div>

            <div class="text-xs text-gray-500 flex">
                Room: ${room?.number || "Storage"}
            </div>

            ${userBadge}

            <div class="flex flex-wrap gap-2 pt-2">
                ${actions}
            </div>
        `;

        container.appendChild(card);
    });

    // --- ACTION HANDLERS ---

    container.querySelectorAll('[data-action="broken"]').forEach(btn => {
        btn.onclick = async () => {
            try {
                await markAssetBroken(btn.dataset.id);
                showToast("Asset marked as broken");
                await loadAssets();
            } catch {
                showToast("Failed", "error");
            }
        };
    });

    container.querySelectorAll('[data-action="maintenance"]').forEach(btn => {
        btn.onclick = async () => {
            try {
                await sendAssetToMaintenance(btn.dataset.id);
                showToast("Sent to maintenance");
                await loadAssets();
            } catch {
                showToast("Failed", "error");
            }
        };
    });

    container.querySelectorAll('[data-action="remove-room"]').forEach(btn => {
        btn.onclick = async () => {
            try {
                await removeAssetFromRoom(btn.dataset.id);
                showToast("Unassigned");
                await loadAssets();
            } catch {
                showToast("Failed", "error");
            }
        };
    });

    container.querySelectorAll('[data-action="assign-room"]').forEach(btn => {
        btn.onclick = () => {
            selectedAssetId = btn.dataset.id;

            const modal = document.getElementById("quick-assign-modal");
            const select = document.getElementById("quick-room-select");

            select.innerHTML = `<option value="">Select room</option>`;

            rooms.forEach(r => {
                const option = document.createElement("option");
                option.value = r.roomId;
                option.textContent = r.number;
                select.appendChild(option);
            });

            modal.classList.remove("hidden");
        };
    });

    container.querySelectorAll('[data-action="available"]').forEach(btn => {
        btn.onclick = async () => {
            try {
                await markAssetAvailable(btn.dataset.id);
                showToast("Now available");
                await loadAssets();
            } catch {
                showToast("Failed", "error");
            }
        };
    });

    const confirmBtn = document.getElementById("quick-assign-confirm");
    const cancelBtn = document.getElementById("quick-assign-cancel");

    confirmBtn.onclick = async () => {
        const roomId = document.getElementById("quick-room-select").value;

        if (!roomId) {
            showToast("Select room", "warning");
            return;
        }

        try {
            await assignAssetToRoom(selectedAssetId, roomId);
            document.getElementById("quick-assign-modal").classList.add("hidden");
            showToast("Assigned");
            await loadAssets();
        } catch {
            showToast("Failed", "error");
        }
    };

    cancelBtn.onclick = () => {
        document.getElementById("quick-assign-modal").classList.add("hidden");
    };
}