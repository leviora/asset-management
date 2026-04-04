// main.js

import { loadAssets, getAssetsData } from "./assets.js";
import { fetchAssetModels, createAsset } from "./api.js";
import { showToast } from "./ui.js";
import { loadRooms } from "./rooms.js";

document.addEventListener("DOMContentLoaded", async () => {
    await loadAssets();
    setupModal();
    setupAssignment();
});

// --- CREATE ASSET MODAL ---

function setupModal() {
    const openBtn = document.getElementById("add-asset-btn");
    const modal = document.getElementById("asset-modal");
    const closeBtn = document.getElementById("close-modal");
    const submitBtn = document.getElementById("submit-asset");

    if (!openBtn || !modal) return;

    openBtn.addEventListener("click", async () => {
        modal.classList.remove("hidden");
        await loadModels();
    });

    closeBtn.addEventListener("click", () => {
        modal.classList.add("hidden");
    });

    submitBtn.addEventListener("click", handleSubmit);
}

async function loadModels() {
    const select = document.getElementById("asset-model-select");

    try {
        const models = await fetchAssetModels();

        select.innerHTML = `<option value="">Select asset model</option>`;

        models.forEach(m => {
            const option = document.createElement("option");
            option.value = m.id;
            option.textContent = `${m.manufacturer} ${m.modelName}`;
            select.appendChild(option);
        });

    } catch (e) {
        showToast("Failed to load models ❌", true);
    }
}

async function handleSubmit() {
    const modelId = document.getElementById("asset-model-select").value;
    const serial = document.getElementById("serial-number").value;
    const type = document.getElementById("asset-type-select").value;

    if (!modelId || !type) {
        showToast("Fill required fields ⚠️", true);
        return;
    }

    try {
        await createAsset({
            assetModelId: modelId,
            serialNumber: serial,
            assetType: type
        });

        showToast("Asset added ✅");

        document.getElementById("asset-modal").classList.add("hidden");

        await loadAssets();

    } catch (e) {
        showToast("Failed to create asset ❌", true);
    }
}

// --- ASSIGNMENT FLOW ---

function setupAssignment() {
    document.getElementById("create-assignment-btn")?.addEventListener("click", async () => {
        document.getElementById("assignment-modal").classList.remove("hidden");
        await populateAssignmentData();
    });

    document.getElementById("close-assignment-modal")?.addEventListener("click", () => {
        document.getElementById("assignment-modal").classList.add("hidden");
    });

    document.getElementById("submit-assignment")?.addEventListener("click", handleAssignment);
}

async function populateAssignmentData() {

    const assets = await getAssetsData();

    const assetSelect = document.getElementById("assign-asset-select");
    assetSelect.innerHTML = `<option value="">Select asset</option>`;

    assets.forEach(a => {
        if (a.status === "AVAILABLE") {
            const option = document.createElement("option");
            option.value = a.id;
            option.textContent = a.serialNumber
                ? `SN: ${a.serialNumber}`
                : `Asset ${a.id}`;
            assetSelect.appendChild(option);
        }
    });

    const rooms = await loadRooms();
    const roomSelect = document.getElementById("assign-room-select");

    roomSelect.innerHTML = `<option value="">Select room</option>`;

    rooms.forEach(r => {
        const option = document.createElement("option");
        option.value = r.roomId;
        option.textContent = r.number;
        roomSelect.appendChild(option);
    });
}

async function handleAssignment() {
    const assetId = document.getElementById("assign-asset-select").value;
    const roomId = document.getElementById("assign-room-select").value;

    if (!assetId || !roomId) {
        showToast("Select asset and room ⚠️", true);
        return;
    }

    try {
        const res = await fetch(`/api/assets/${assetId}/assign-room/${roomId}`, {
            method: "PATCH"
        });

        if (!res.ok) throw new Error();

        showToast("Assigned ✅");

        document.getElementById("assignment-modal").classList.add("hidden");

        await loadAssets();

    } catch (e) {
        showToast("Assign failed ❌", true);
    }
}