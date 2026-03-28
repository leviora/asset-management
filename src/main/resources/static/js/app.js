document.addEventListener("DOMContentLoaded", function () {

    const openBtn = document.getElementById("add-asset-btn");
    const modal = document.getElementById("asset-modal");
    const closeBtn = document.getElementById("close-modal");
    const submitBtn = document.getElementById("submit-asset");

    const modelSelect = document.getElementById("asset-model-select");
    const typeSelect = document.getElementById("asset-type-select");
    const serialInput = document.getElementById("serial-number");

    const openModal = () => {
        modal.classList.remove("hidden");
        loadAssetModels();
};

openBtn.addEventListener("click", openModal);

    // CLOSE MODAL
    closeBtn.addEventListener("click", function () {
        modal.classList.add("hidden");
    });

    // SUBMIT
    submitBtn.addEventListener("click", async function () {

        const assetModelId = modelSelect.value;
        const assetType = typeSelect.value;
        const serialNumber = serialInput.value;

        if (!assetModelId) {
            alert("Select asset model!");
            return;
        }

        if (!assetType) {
            alert("Select asset type!");
            return;
        }

//        await fetch("/assets", {
//            method: "POST",
//            headers: {
//                "Content-Type": "application/json"
//            },
//            body: JSON.stringify({
//                assetModelId: assetModelId,
//                assetType: assetType,
//                serialNumber: serialNumber || null
//            })
//        });

try {
    const response = await fetch("/api/assets", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            assetModelId: assetModelId,
            assetType: assetType,
            serialNumber: serialNumber || null
        })
    });

    if (!response.ok) {
        throw new Error("Failed to create asset");
    }

    showToast("Asset created successfully ✅");

    modal.classList.add("hidden");
    serialInput.value = "";
    modelSelect.value = "";
    typeSelect.value = "";

} catch (err) {
    showToast("Something went wrong ❌", true);
}

        // Reset modal
        modal.classList.add("hidden");
        serialInput.value = "";
        modelSelect.value = "";
        typeSelect.value = ""; // <- reset dropdown
    });

    async function loadAssetModels() {
        const response = await fetch("/api/asset-models");
        const data = await response.json();

        modelSelect.innerHTML = '<option value="">Select asset model</option>';

        data.forEach(model => {
            const option = document.createElement("option");
            option.value = model.id;
            option.textContent = model.manufacturer + " " + model.modelName;
            modelSelect.appendChild(option);
        });
    }

    function showToast(message, isError = false) {
        const toast = document.getElementById("toast");

        toast.textContent = message;
        toast.classList.remove("opacity-0");

        toast.classList.remove("bg-green-600", "bg-red-600");
        toast.classList.add(isError ? "bg-red-600" : "bg-green-600");

        setTimeout(() => {
            toast.classList.add("opacity-0");
        }, 3000);
    }
});