// dashboard.js

import { loadAssets } from "./assets.js";
import { refreshActivityLogs } from "./activity.js";
import { dashboardState } from "./state.js";

export async function refreshDashboard() {

    await Promise.all([
        loadAssets({
            ...dashboardState.filters,
            page: dashboardState.page,
            size: dashboardState.size,
            sort: dashboardState.sort
        }),

        refreshActivityLogs()
    ]);
}