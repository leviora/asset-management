package pl.school.assetmanagement.application.port.in;

import pl.school.assetmanagement.domain.model.AssetModel;
import pl.school.assetmanagement.domain.model.enums.AssetType;

public interface CreateAssetModel {

    AssetModel create(
            String manufacturer,
            String modelName,
            AssetType assetType
    );
}
