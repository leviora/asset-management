// asset-stats.js

export function renderStats(stats) {

    const totalEl = document.getElementById("total-assets");
    const availableEl = document.getElementById("available-assets");
    const inUseEl = document.getElementById("in-use-assets");
    const maintenanceEl = document.getElementById("maintenance-assets");
    const brokenEl = document.getElementById("broken-assets");

    if (totalEl) totalEl.textContent = stats.total;
    if (availableEl) availableEl.textContent = stats.available;
    if (inUseEl) inUseEl.textContent = stats.inUse;
    if (maintenanceEl) maintenanceEl.textContent = stats.maintenance;
    if (brokenEl) brokenEl.textContent = stats.broken;
}