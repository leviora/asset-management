import { fetchAssetModels, createAsset } from "./api.js";
import { showToast } from "./ui.js";
import { loadAssets } from "./assets.js";

export function initAddAssetModal() {
    const openBtn = document.getElementById("add-asset-btn");
    const modal = document.getElementById("asset-modal");
    const closeBtn = document.getElementById("close-modal");
    const submitBtn = document.getElementById("submit-asset");

    const modelSelect = document.getElementById("asset-model-select");
    const typeSelect = document.getElementById("asset-type-select");
    const serialInput = document.getElementById("serial-number");

    if (!modal) return; // ważne – żeby nie crashowało na innych stronach

    // OPEN
    openBtn?.addEventListener("click", async () => {
        modal.classList.remove("hidden");
        await loadModels();
    });

    // CLOSE
    closeBtn?.addEventListener("click", () => {
        modal.classList.add("hidden");
    });

    // SUBMIT
    submitBtn?.addEventListener("click", async () => {
        const payload = {
            assetModelId: modelSelect.value,
            assetType: typeSelect.value,
            serialNumber: serialInput.value || null
        };

        if (!payload.assetModelId) {
            showToast("Select model ❌", true);
            return;
        }

        if (!payload.assetType) {
            showToast("Select type ❌", true);
            return;
        }

        try {
            await createAsset(payload);

            showToast("Asset created ✅");

            modal.classList.add("hidden");

            serialInput.value = "";
            modelSelect.value = "";
            typeSelect.value = "";

            loadAssets();

        } catch (err) {
            showToast("Error creating asset ❌", true);
        }
    });

    async function loadModels() {
        try {
            const models = await fetchAssetModels();

            modelSelect.innerHTML = '<option value="">Select model</option>';

            models.forEach(m => {
                const opt = document.createElement("option");
                opt.value = m.id;
                opt.textContent = m.modelName || `Model ${m.id}`;
                modelSelect.appendChild(opt);
            });

        } catch (err) {
            showToast("Failed to load models ❌", true);
        }
    }
}