package pl.school.assetmanagement.adapter.in.rest.activitylog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.school.assetmanagement.adapter.in.rest.activitylog.dto.ActivityLogResponse;
import pl.school.assetmanagement.application.port.out.AssetRepository;
import pl.school.assetmanagement.domain.model.ActivityLog;
import pl.school.assetmanagement.domain.model.Asset;

@Component
@RequiredArgsConstructor
public class ActivityLogWebMapper {

    private final AssetRepository assetRepository;

    public ActivityLogResponse toResponse(ActivityLog log) {

        Asset asset = assetRepository.findById(log.getAssetId())
                .orElse(null);

        return new ActivityLogResponse(
                log.getId().value(),
                log.getType(),
                log.getAssetId().value(),
                asset != null ? asset.getAssetType().name() : "UNKNOWN",
                asset != null ? asset.getSerialNumber() : null,
                log.getFromRoom() != null ? log.getFromRoom().value() : null,
                log.getToRoom() != null ? log.getToRoom().value() : null,
                log.getPerformedBy(),
                log.getTimestamp()
        );
    }
}