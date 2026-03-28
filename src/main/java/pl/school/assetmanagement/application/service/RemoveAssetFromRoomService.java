package pl.school.assetmanagement.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.school.assetmanagement.application.port.in.RemoveAssetFromRoom;
import pl.school.assetmanagement.application.port.out.AssetRepository;
import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.AssetId;

@Service
@RequiredArgsConstructor
public class RemoveAssetFromRoomService implements RemoveAssetFromRoom {

    private final AssetRepository assetRepository;

    @Override
    public void remove(AssetId assetId) {

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new IllegalArgumentException("Asset not found"));

        asset.removeFromRoom();

        assetRepository.save(asset);
    }
}
