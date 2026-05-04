package pl.school.assetmanagement.adapter.in.rest.activitylog.dto;

import pl.school.assetmanagement.domain.model.enums.ActivityType;

import java.time.Instant;
import java.util.UUID;

public record ActivityLogResponse(
        UUID id,
        ActivityType type,
        UUID assetId,
        UUID fromRoomId,
        UUID toRoomId,
        String performedBy,
        Instant timestamp
) {
}
