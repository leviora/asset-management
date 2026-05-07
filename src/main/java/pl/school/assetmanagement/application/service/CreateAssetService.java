package pl.school.assetmanagement.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.school.assetmanagement.adapter.out.persistence.assetmodel.AssetModelJpaRepository;
import pl.school.assetmanagement.application.event.AssetActivityEvent;
import pl.school.assetmanagement.application.port.in.CreateAsset;
import pl.school.assetmanagement.application.port.out.AssetRepository;
import pl.school.assetmanagement.application.port.out.RoomRepository;
import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.AssetId;
import pl.school.assetmanagement.domain.model.AssetModelId;
import pl.school.assetmanagement.domain.model.Room;
import pl.school.assetmanagement.domain.model.enums.ActivityType;
import pl.school.assetmanagement.domain.model.enums.AssetType;
import org.springframework.context.ApplicationEventPublisher;

@Service
@RequiredArgsConstructor
public class CreateAssetService implements CreateAsset {

    private final AssetRepository assetRepository;
    private final AssetModelJpaRepository assetModelJpaRepository;
    private final RoomRepository roomRepository;
    private final ApplicationEventPublisher eventPublisher;


    @Override
    public AssetId create(AssetModelId assetModelId, String serialNumber, AssetType assetType) {

        assetModelJpaRepository.findById(assetModelId.value())
                .orElseThrow(() -> new RuntimeException("Model not found"));

        Room storage = roomRepository.findStorageRoom()
                .orElseThrow(() -> new IllegalStateException("Storage room not found"));

        Asset asset = Asset.createNew(
                assetModelId,
                assetType,
                storage.getRoomId()
        );

        asset.assignSerialNumber(serialNumber);

        Asset saved = assetRepository.save(asset);

        eventPublisher.publishEvent(
                new AssetActivityEvent(
                        ActivityType.CREATED,
                        saved.getId(),
                        null,
                        storage.getRoomId()
                )
        );

        return saved.getId();
    }
}
