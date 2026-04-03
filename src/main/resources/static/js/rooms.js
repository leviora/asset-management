// rooms.js

export async function loadRooms() {
    const response = await fetch('/api/rooms');

    if (!response.ok) {
        console.error("Rooms fetch failed:", response.status);
        return [];
    }

    return await response.json();
}