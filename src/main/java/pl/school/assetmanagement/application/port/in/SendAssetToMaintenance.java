package pl.school.assetmanagement.application.port.in;

import pl.school.assetmanagement.domain.model.AssetId;

public interface SendAssetToMaintenance {

    void send(AssetId assetId);
}
