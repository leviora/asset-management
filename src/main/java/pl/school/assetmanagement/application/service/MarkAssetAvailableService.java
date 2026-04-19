package pl.school.assetmanagement.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.school.assetmanagement.application.port.in.MarkAssetAvailable;
import pl.school.assetmanagement.application.port.out.AssetRepository;
import pl.school.assetmanagement.application.port.out.RoomRepository;
import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.AssetId;
import pl.school.assetmanagement.domain.model.Room;

@Service
@RequiredArgsConstructor
public class MarkAssetAvailableService implements MarkAssetAvailable {

    private final AssetRepository assetRepository;
    private final RoomRepository roomRepository;

    @Override
    public void mark(AssetId assetId) {

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new IllegalArgumentException("Asset not found"));

        Room storage = roomRepository.findStorageRoom()
                .orElseThrow(() -> new IllegalStateException("Storage room not found"));

        asset.markAsAvailable(storage.getRoomId());

        assetRepository.save(asset);
    }
}
