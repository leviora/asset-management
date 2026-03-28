package pl.school.assetmanagement.application.port.in;

import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.AssetId;

public interface GetAsset {

    Asset get(AssetId assetId);
}
