package pl.school.assetmanagement.adapter.out.persistence.assetmodel;

import org.springframework.stereotype.Component;
import pl.school.assetmanagement.domain.model.AssetModel;
import pl.school.assetmanagement.domain.model.AssetModelId;

@Component
public class AssetModelJpaMapper {

    public AssetModelJpaEntity toEntity(AssetModel model) {
        if (model == null) return null;

        AssetModelJpaEntity entity = new AssetModelJpaEntity();
        entity.setId(model.getId().value());
        entity.setManufacturer(model.getManufacturer());
        entity.setModelName(model.getModelName());
        entity.setAssetType(model.getAssetType());

        return entity;
    }

    public AssetModel toDomain(AssetModelJpaEntity entity) {
        if (entity == null) return null;

        return AssetModel.restore(
                new AssetModelId(entity.getId()),
                entity.getManufacturer(),
                entity.getModelName(),
                entity.getAssetType()
        );
    }
}