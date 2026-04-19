package pl.school.assetmanagement.application.port.in;

import pl.school.assetmanagement.domain.model.AssetId;

public interface MarkAssetAvailable {
    void mark(AssetId assetId);
}
