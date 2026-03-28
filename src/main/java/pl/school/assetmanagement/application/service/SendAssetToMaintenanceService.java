package pl.school.assetmanagement.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.school.assetmanagement.application.port.in.SendAssetToMaintenance;
import pl.school.assetmanagement.application.port.out.AssetRepository;
import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.AssetId;

@Service
@RequiredArgsConstructor
public class SendAssetToMaintenanceService implements SendAssetToMaintenance {

    private final AssetRepository assetRepository;

    @Override
    public void send(AssetId assetId) {

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new IllegalArgumentException("Asset not found"));

        asset.sendToMaintenance();

        assetRepository.save(asset);
    }
}
