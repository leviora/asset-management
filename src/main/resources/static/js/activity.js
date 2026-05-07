// activity.js

export async function refreshActivityLogs() {

    const res = await fetch('/api/logs/today');
    const logs = await res.json();

    const container = document.getElementById('activity-container');

    if (!container) return;

    container.innerHTML = '';

    logs.reverse().forEach(log => {

        const div = document.createElement('div');

        div.className =
            "flex justify-between border-b border-white/5 pb-3";

        div.innerHTML = `
            <div>

                <p class="text-sm font-semibold text-orange-300">
                    ${formatActivityType(log.type)}
                </p>

                <p class="text-xs text-gray-400 mt-1">
                    ${log.assetType}
                    ${log.serialNumber ? `• ${log.serialNumber}` : ""}
                </p>

            </div>

            <div class="text-gray-500 text-xs">
                ${formatTime(log.timestamp)}
            </div>
        `;

        container.appendChild(div);
    });
}

function formatActivityType(type) {

    const labels = {
        ASSIGNED_TO_ROOM: "Assigned to room",
        REMOVED_FROM_ROOM: "Removed from room",
        SENT_TO_MAINTENANCE: "Sent to maintenance",
        MARKED_AS_BROKEN: "Marked as broken",
        MARKED_AS_AVAILABLE: "Marked as available",
        CREATED: "Asset created"
    };

    return labels[type] || type;
}

function formatTime(timestamp) {

    return new Date(timestamp)
        .toLocaleTimeString([], {
            hour: "2-digit",
            minute: "2-digit"
        });
}