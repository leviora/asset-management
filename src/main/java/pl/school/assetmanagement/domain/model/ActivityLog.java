package pl.school.assetmanagement.domain.model;

import lombok.Getter;
import pl.school.assetmanagement.domain.model.enums.ActivityType;

import java.time.Instant;

@Getter
public class ActivityLog {

    private final ActivityLogId id;
    private final Instant timestamp;
    private final ActivityType type;
    private final AssetId assetId;
    private final RoomId fromRoom;
    private final RoomId toRoom;
    private final String performedBy;

    private ActivityLog(
            ActivityLogId id,
            Instant timestamp,
            ActivityType type,
            AssetId assetId,
            RoomId fromRoom,
            RoomId toRoom,
            String performedBy
    ) {
        this.id = id;
        this.timestamp = timestamp;
        this.type = type;
        this.assetId = assetId;
        this.fromRoom = fromRoom;
        this.toRoom = toRoom;
        this.performedBy = performedBy;
    }

    public static ActivityLog create(
            ActivityType type,
            AssetId assetId,
            RoomId fromRoom,
            RoomId toRoom,
            String performedBy
    ) {
        return new ActivityLog(
                ActivityLogId.newId(),
                Instant.now(),
                type,
                assetId,
                fromRoom,
                toRoom,
                performedBy
        );
    }


    public static ActivityLog restore(
            ActivityLogId id,
            Instant timestamp,
            ActivityType type,
            AssetId assetId,
            RoomId fromRoom,
            RoomId toRoom,
            String performedBy
    ) {
        return new ActivityLog(
                id,
                timestamp,
                type,
                assetId,
                fromRoom,
                toRoom,
                performedBy
        );
    }
}
