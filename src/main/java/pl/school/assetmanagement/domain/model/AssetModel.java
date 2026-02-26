package pl.school.assetmanagement.domain.model;

import lombok.Getter;
import pl.school.assetmanagement.domain.model.enums.AssetType;

import java.util.UUID;

@Getter
public class AssetModel {

    private final AssetModelId id;
    private final String manufacturer;
    private final String modelName;
    private final AssetType assetType;

    private AssetModel(
            AssetModelId id,
            String manufacturer,
            String modelName,
            AssetType assetType
    ) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.modelName = modelName;
        this.assetType = assetType;
    }

    public static AssetModel createNew(
            String manufacturer,
            String modelName,
            AssetType assetType
    ) {
        if (manufacturer == null || manufacturer.isBlank()) {
            throw new IllegalArgumentException("Manufacturer is required");
        }
        if (modelName == null || modelName.isBlank()) {
            throw new IllegalArgumentException("Model name is required");
        }
        if (assetType == null) {
            throw new IllegalArgumentException("Asset type is required");
        }

        return new AssetModel(
                AssetModelId.newId(),
                manufacturer,
                modelName,
                assetType
        );
    }

    public static AssetModel restore(
            AssetModelId id,
            String manufacturer,
            String modelName,
            AssetType assetType
    ) {
        return new AssetModel(id, manufacturer, modelName, assetType);
    }
}
