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