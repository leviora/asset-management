// asset-actions.js

import { showToast } from "./ui.js";
import { refreshDashboard } from "./dashboard.js";

export async function executeAssetAction(action, successMessage) {

    try {

        await action();

        showToast(successMessage);

        await refreshDashboard();

    } catch (err) {

        console.error(err);

        showToast("Failed", "error");
    }
}