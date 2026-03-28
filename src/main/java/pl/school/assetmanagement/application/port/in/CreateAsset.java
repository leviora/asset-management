package pl.school.assetmanagement.application.port.in;

import pl.school.assetmanagement.domain.model.AssetId;
import pl.school.assetmanagement.domain.model.AssetModelId;
import pl.school.assetmanagement.domain.model.enums.AssetType;

public interface CreateAsset {

    AssetId create(AssetModelId assetModelId, String serialNumber, AssetType assetType);
}
