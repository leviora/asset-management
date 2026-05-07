// assets.js

import { fetchAssets } from "./api.js";
import { showToast } from "./ui.js";
import {
    assignAssetToRoom,
    fetchAssetStats,
} from "./api.js";

import { loadRooms } from "./rooms.js";
import { executeAssetAction } from "./asset-actions.js";
import { assetActionMap } from "./asset-action-map.js";
import { renderStats } from "./asset-stats.js";
import { renderPagination } from "./asset-pagination.js";
import { renderAssetCard } from "./asset-card.js";

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

        const pageData = await fetchAssets(filters);

        const assets = pageData.content || [];

        const stats = await fetchAssetStats();

        renderStats(stats);

        const rooms = await loadRooms();

        const container = document.getElementById("assets-container");

        if (container) {

            renderAssets(container, assets, rooms);
        }

        renderPagination(pageData);

        return assets;

    } catch (err) {

        console.error(err);

        showToast("Failed to load assets ❌", true);

        return [];
    }
}

function renderAssets(container, assets, rooms) {

    container.innerHTML = "";

    if (!assets.length) {

        container.innerHTML = `
            <p class="text-gray-400">
                No assets
            </p>
        `;

        return;
    }

    assets.forEach(asset => {

        const room = rooms.find(
            r => r.roomId === asset.roomId
        );

        const card = renderAssetCard(
            asset,
            room,
            baseBtnClass
        );

        container.appendChild(card);
    });

    bindAssetActions(container, rooms);
}

function bindAssetActions(container, rooms) {

    container.onclick = async (e) => {

        const button = e.target.closest("[data-action]");

        if (!button) return;

        const action = button.dataset.action;

        const assetId = button.dataset.id;

        const config = assetActionMap[action];

        if (config) {

            await executeAssetAction(
                () => config.handler(assetId),
                config.successMessage
            );

            return;
        }

        if (action === "assign-room") {

            selectedAssetId = assetId;

            const modal =
                document.getElementById(
                    "quick-assign-modal"
                );

            const select =
                document.getElementById(
                    "quick-room-select"
                );

            select.innerHTML = `
                <option value="">
                    Select room
                </option>
            `;

            rooms.forEach(r => {

                const option =
                    document.createElement("option");

                option.value = r.roomId;

                option.textContent = r.number;

                select.appendChild(option);
            });

            modal.classList.remove("hidden");
        }
    };

    const confirmBtn =
        document.getElementById(
            "quick-assign-confirm"
        );

    const cancelBtn =
        document.getElementById(
            "quick-assign-cancel"
        );

    confirmBtn.onclick = async () => {

        const roomId =
            document.getElementById(
                "quick-room-select"
            ).value;

        if (!roomId) {

            showToast("Select room", "warning");

            return;
        }

        await executeAssetAction(
            () => assignAssetToRoom(
                selectedAssetId,
                roomId
            ),
            "Assigned"
        );

        document.getElementById(
            "quick-assign-modal"
        ).classList.add("hidden");
    };

    cancelBtn.onclick = () => {

        document.getElementById(
            "quick-assign-modal"
        ).classList.add("hidden");
    };
}