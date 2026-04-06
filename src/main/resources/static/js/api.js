// api.js

export async function fetchAssets() {
    const response = await fetch('/api/assets');

    if (!response.ok) {
        console.error("Assets fetch failed:", response.status);
        return [];
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