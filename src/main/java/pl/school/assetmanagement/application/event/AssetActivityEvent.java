package pl.school.assetmanagement.application.event;

import pl.school.assetmanagement.domain.model.AssetId;
import pl.school.assetmanagement.domain.model.RoomId;
import pl.school.assetmanagement.domain.model.enums.ActivityType;

public record AssetActivityEvent(
        ActivityType type,
        AssetId assetId,
        RoomId fromRoom,
        RoomId toRoom
) {
}
