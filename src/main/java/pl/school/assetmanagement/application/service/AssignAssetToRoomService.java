package pl.school.assetmanagement.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.school.assetmanagement.application.event.AssetActivityEvent;
import pl.school.assetmanagement.application.port.in.AssignAssetToRoom;
import pl.school.assetmanagement.application.port.out.AssetRepository;
import pl.school.assetmanagement.application.port.out.RoomRepository;
import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.AssetId;
import pl.school.assetmanagement.domain.model.RoomId;
import pl.school.assetmanagement.domain.model.enums.ActivityType;

@Service
@RequiredArgsConstructor
public class AssignAssetToRoomService implements AssignAssetToRoom {

    private final AssetRepository assetRepository;
    private final RoomRepository roomRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void assign(AssetId assetId, RoomId roomId) {

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found"));

        RoomId previousRoom = asset.getRoomId();

        roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        asset.assignToRoom(roomId);

        assetRepository.save(asset);

        eventPublisher.publishEvent(
                new AssetActivityEvent(
                        ActivityType.ASSIGNED_TO_ROOM,
                        assetId,
                        previousRoom,
                        roomId
                )
        );
    }
}