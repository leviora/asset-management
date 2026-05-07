// asset-card.js

const baseBtnClass = `
    px-3 py-1 text-xs rounded
    bg-orange-500/10 text-orange-300
    hover:bg-orange-500 hover:text-black
    transition
`;

export function renderAssetCard(asset, room, baseBtnClass) {

    const card = document.createElement("div");

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

            <h3 class="text-lg font-bold">
                ${asset.assetType}
            </h3>

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

    return card;
}