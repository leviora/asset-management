// assets.js

import { fetchAssets } from "./api.js";
import { showToast } from "./ui.js";

// Main function used by dashboard
export async function loadAssets() {
    try {
        const assets = await fetchAssets();

        renderStats(assets);

        const container = document.getElementById("assets-container");
        if (container) {
            renderAssets(container, assets);
        }

        return assets; // IMPORTANT: return data for reuse
    } catch (err) {
        showToast("Failed to load assets ❌", true);
        console.error(err);
        return [];
    }
}

// --- UI helpers ---

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
        case "IN_USE": return "bg-green-100 text-green-700";
        case "AVAILABLE": return "bg-blue-100 text-blue-700";
        case "MAINTENANCE": return "bg-yellow-100 text-yellow-700";
        case "BROKEN": return "bg-red-100 text-red-700";
        default: return "bg-gray-100 text-gray-600";
    }
}

function renderAssets(container, assets) {
    container.innerHTML = "";

    if (!assets.length) {
        container.innerHTML = `<p class="text-gray-400">No assets</p>`;
        return;
    }

    assets.forEach(asset => {
        const card = document.createElement("div");

        card.className = `
            p-6 rounded-xl bg-white shadow hover:shadow-xl transition
        `;

        card.innerHTML = `
            <div class="flex justify-between mb-3">
                <span class="${getStatusClass(asset.status)} px-2 py-1 text-xs rounded">
                    ${asset.status}
                </span>
            </div>

            <h4 class="font-bold">${asset.assetType}</h4>

            <p class="text-xs text-gray-500 mb-2">
                SN: ${asset.serialNumber || "N/A"}
            </p>
        `;

        container.appendChild(card);
    });
}