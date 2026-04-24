// api.js

export async function fetchAssets(filters = {}) {
    const params = new URLSearchParams();

    if (filters.serialNumber) {
        params.append("serialNumber", filters.serialNumber);
    }

    if (filters.status) {
        params.append("status", filters.status);
    }

    if (filters.assetType) {
        params.append("assetType", filters.assetType);
    }

    // 🔥 PAGINATION
    params.append("page", filters.page ?? 0);
    params.append("size", filters.size ?? 6);

    // 🔥 SORTING
    if (filters.sort) {
        params.append("sort", filters.sort);
    }

    const response = await fetch(`/api/assets?${params.toString()}`);

    if (!response.ok) {
        console.error("Assets fetch failed:", response.status);
        return {
            content: [],
            totalPages: 0,
            number: 0
        };
    }

    return await response.json();
}

export async function fetchAssetModels() {
    const res = await fetch("/api/asset-models");

    if (!res.ok) {
        console.error("Models fetch failed");
        return [];
    }

    return await res.json();
}

export async function createAsset(payload) {
    const res = await fetch("/api/assets", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    });

    if (!res.ok) {
        const text = await res.text();
        console.error(text);
        throw new Error("Failed to create asset");
    }
}

export async function markAssetBroken(assetId) {
    const res = await fetch(`/api/assets/${assetId}/broken`, {
        method: "PATCH"
    });

    if (!res.ok) {
        throw new Error("Failed to mark as broken");
    }
}



export async function sendAssetToMaintenance(assetId) {
    const res = await fetch(`/api/assets/${assetId}/maintenance`, {
        method: "PATCH"
    });

    if (!res.ok) {
        throw new Error("Failed to send to maintenance");
    }
}

export async function removeAssetFromRoom(assetId) {
    const res = await fetch(`/api/assets/${assetId}/remove-room`, {
        method: "PATCH"
    });

    if (!res.ok) {
        throw new Error("Failed to remove from room");
    }
}

export async function assignAssetToRoom(assetId, roomId) {
    const res = await fetch(`/api/assets/${assetId}/assign-room/${roomId}`, {
        method: "PATCH"
    });

    if (!res.ok) throw new Error("Assign failed");
}

export async function markAssetAvailable(assetId) {
    const res = await fetch(`/api/assets/${assetId}/available`, {
        method: "PATCH"
    });

    if (!res.ok) {
        throw new Error("Failed to mark as available");
    }
}

export const ASSET_TYPES = [
    "COMPUTER",
    "PROJECTOR",
    "MONITOR",
    "PRINTER",
    "KEYBOARD",
    "MOUSE",
    "SPEAKER",
    "HEADSET",
    "TABLET",
    "HARDDRIVE",
    "GRAPHICCARD",
    "OTHER"
];

export async function fetchAssetStats() {
    const response = await fetch("/api/assets/stats");

    if (!response.ok) {
        throw new Error("Failed to fetch stats");
    }

    return await response.json();
}