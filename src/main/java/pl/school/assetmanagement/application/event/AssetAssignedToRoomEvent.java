package pl.school.assetmanagement.application.event;

import lombok.Getter;
import pl.school.assetmanagement.domain.model.AssetId;
import pl.school.assetmanagement.domain.model.RoomId;

@Getter
public class AssetAssignedToRoomEvent {

    private final AssetId assetId;
    private final RoomId previousRoom;
    private final RoomId newRoom;

    public AssetAssignedToRoomEvent(
            AssetId assetId,
            RoomId previousRoom,
            RoomId newRoom
    ) {
        this.assetId = assetId;
        this.previousRoom = previousRoom;
        this.newRoom = newRoom;
    }
}