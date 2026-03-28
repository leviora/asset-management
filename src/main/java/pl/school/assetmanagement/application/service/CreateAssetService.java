package pl.school.assetmanagement.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.school.assetmanagement.adapter.out.persistence.asset.AssetJpaMapper;
import pl.school.assetmanagement.adapter.out.persistence.assetmodel.AssetModelJpaRepository;
import pl.school.assetmanagement.application.port.in.CreateAsset;
import pl.school.assetmanagement.application.port.out.AssetRepository;
import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.AssetId;
import pl.school.assetmanagement.domain.model.AssetModelId;
import pl.school.assetmanagement.domain.model.enums.AssetType;

@Service
@RequiredArgsConstructor
public class CreateAssetService implements CreateAsset {

    private final AssetRepository assetRepository;
    private final AssetModelJpaRepository assetModelJpaRepository;
    private final AssetJpaMapper assetJpaMapper;

    @Override
    public AssetId create(AssetModelId assetModelId, String serialNumber, AssetType assetType) {
        assetModelJpaRepository.findById(assetModelId.value())
                .orElseThrow(() -> new RuntimeException("Model not found"));

        Asset asset = Asset.createNew(assetModelId, assetType);

        asset.assignSerialNumber(serialNumber);

        Asset saved = assetRepository.save(asset);

        return saved.getId();
    }
}
