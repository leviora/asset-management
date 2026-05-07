// asset-action-map.js

import {
    markAssetBroken,
    sendAssetToMaintenance,
    removeAssetFromRoom,
    markAssetAvailable
} from "./api.js";

export const assetActionMap = {

    broken: {
        handler: markAssetBroken,
        successMessage: "Asset marked as broken"
    },

    maintenance: {
        handler: sendAssetToMaintenance,
        successMessage: "Sent to maintenance"
    },

    "remove-room": {
        handler: removeAssetFromRoom,
        successMessage: "Unassigned"
    },

    available: {
        handler: markAssetAvailable,
        successMessage: "Now available"
    }
};