package pl.school.assetmanagement.adapter.out.persistence.asset;

import org.springframework.stereotype.Component;
import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.AssetId;
import pl.school.assetmanagement.domain.model.AssetModelId;
import pl.school.assetmanagement.domain.model.RoomId;

@Component
public class AssetJpaMapper {

    public AssetJpaEntity toEntity(Asset asset) {
        AssetJpaEntity entity = new AssetJpaEntity();

        entity.setId(asset.getId().value());
        entity.setAssetModelId(asset.getAssetModelId().value());
        entity.setStatus(asset.getStatus());
        entity.setSerialNumber(asset.getSerialNumber());
        entity.setRoomId(asset.getRoomId() != null ? asset.getRoomId().value() : null);
        entity.setAssetType(asset.getAssetType());

        return entity;
    }

    public Asset toDomain(AssetJpaEntity entity) {
        return Asset.restore(
                new AssetId(entity.getId()),
                new AssetModelId(entity.getAssetModelId()),
                entity.getAssetType(),
                entity.getStatus(),
                entity.getSerialNumber(),
                entity.getRoomId() != null ? new RoomId(entity.getRoomId()) : null
        );
    }
}