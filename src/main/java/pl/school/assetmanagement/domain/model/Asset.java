package pl.school.assetmanagement.domain.model;

import lombok.Getter;
import pl.school.assetmanagement.domain.model.enums.AssetStatus;
import pl.school.assetmanagement.domain.model.enums.AssetType;

@Getter
public class Asset {

    private final AssetId id;
    private final AssetModelId assetModelId;
    private AssetStatus status;
    private String serialNumber;
    private RoomId roomId;
    private AssetType assetType;

    private Asset(
            AssetId id,
            AssetModelId assetModelId,
            AssetType assetType,
            AssetStatus status,
            String serialNumber,
            RoomId roomId
    ) {
        this.id = id;
        this.assetModelId = assetModelId;
        this.assetType = assetType;
        this.status = status;
        this.serialNumber = serialNumber;
        this.roomId = roomId;
    }

    public static Asset createNew(
            AssetModelId assetModelId,
            AssetType assetType,
            RoomId initialRoomId
    ) {
        if (assetModelId == null) {
            throw new IllegalArgumentException("Asset Model id is required");
        }

        if (initialRoomId == null) {
            throw new IllegalArgumentException("Room is required");
        }

        AssetType typeToUse = assetType != null ? assetType : AssetType.OTHER;

        return new Asset(
                AssetId.newAssetId(),
                assetModelId,
                typeToUse,
                AssetStatus.AVAILABLE,
                null,
                initialRoomId
        );
    }

    public void assignSerialNumber(String serialNumber) {
        if (serialNumber == null || serialNumber.isBlank()) {
            return;
        }
        if (this.serialNumber != null) {
            throw new IllegalStateException("Serial number already set");
        }
        this.serialNumber = serialNumber;
    }

    public static Asset restore(
            AssetId id,
            AssetModelId assetModelId,
            AssetType assetType,
            AssetStatus status,
            String serialNumber,
            RoomId roomId) {
        if (id == null || assetModelId == null || status == null) {
            throw new IllegalArgumentException("Invalid asset state");
        }
        return new Asset(id, assetModelId, assetType, status, serialNumber, roomId);
    }

    public void markAsBroken(RoomId storageId) {
        if (this.status != AssetStatus.MAINTENANCE) {
            throw new IllegalStateException("Asset must go through maintenance first");
        }

        this.roomId = storageId;
        this.status = AssetStatus.BROKEN;
    }

    public void sendToMaintenance(RoomId storageId) {
        if (this.status != AssetStatus.IN_USE && this.status != AssetStatus.AVAILABLE) {
            throw new IllegalStateException("Cannot send this asset to maintenance");
        }

        this.roomId = storageId;
        this.status = AssetStatus.MAINTENANCE;
    }

    public void markAsAvailable(RoomId storageId) {
        if (this.status != AssetStatus.MAINTENANCE) {
            throw new IllegalStateException("Only MAINTENANCE asset can be marked available");
        }

        this.roomId = storageId;
        this.status = AssetStatus.AVAILABLE;
    }

    public void assignToRoom(RoomId newRoomId) {
        if (this.status != AssetStatus.AVAILABLE && this.status != AssetStatus.IN_USE) {
            throw new IllegalStateException("Asset cannot be assigned in current state");
        }

        if (newRoomId == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }

        this.roomId = newRoomId;
        this.status = AssetStatus.IN_USE;
    }

    public void removeFromRoom(RoomId storageId) {
        if (this.status != AssetStatus.IN_USE) {
            throw new IllegalStateException("Only IN_USE asset can be unassigned");
        }

        this.roomId = storageId;
        this.status = AssetStatus.AVAILABLE;
    }
}


