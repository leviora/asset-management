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

    public static Asset createNew(AssetModelId assetModelId, AssetType assetType) {
        if (assetModelId == null) {
            throw new IllegalArgumentException("Asset Model id is required");
        }
        AssetType typeToUse = assetType != null ? assetType : AssetType.OTHER;
        return new Asset(AssetId.newAssetId(), assetModelId, typeToUse, AssetStatus.AVAILABLE, null, null);
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

    public void markAsBroken() {
        if (this.status == AssetStatus.BROKEN) {
            throw new IllegalStateException("Asset is already broken");
        }
        this.status = AssetStatus.BROKEN;
    }

    public void sendToMaintenance() {
        if (this.status == AssetStatus.BROKEN) {
            this.status = AssetStatus.MAINTENANCE;
            return;
        }
        throw new IllegalStateException("Only broken asset can be sent to maintenance");
    }

    public void markAsAvailable() {
        if (this.status == AssetStatus.MAINTENANCE) {
            this.status = AssetStatus.AVAILABLE;
            return;
        }
        throw new IllegalStateException("Only asset from maintenance can be marked as available");
    }

    public void assignToRoom(RoomId roomId) {
        if (this.status != AssetStatus.AVAILABLE) {
            throw new IllegalStateException("Only available asset can be assigned to room");
        }
        if (roomId == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        if (this.roomId != null) {
            throw new IllegalStateException("Asset already assigned to a room");
        }
        this.roomId = roomId;
    }

    public void removeFromRoom() {
        if (this.roomId == null) {
            throw new IllegalStateException("Asset is not assigned to any room");
        }
        this.roomId = null;
    }
}


