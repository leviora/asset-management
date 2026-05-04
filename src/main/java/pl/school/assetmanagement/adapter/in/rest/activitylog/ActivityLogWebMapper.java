package pl.school.assetmanagement.adapter.in.rest.activitylog;

import org.springframework.stereotype.Component;
import pl.school.assetmanagement.adapter.in.rest.activitylog.dto.ActivityLogResponse;
import pl.school.assetmanagement.domain.model.ActivityLog;

@Component
public class ActivityLogWebMapper {

    public ActivityLogResponse toResponse(ActivityLog log) {
        return new ActivityLogResponse(
                log.getId().value(),
                log.getType(),
                log.getAssetId().value(),
                log.getFromRoom() != null ? log.getFromRoom().value() : null,
                log.getToRoom() != null ? log.getToRoom().value() : null,
                log.getPerformedBy(),
                log.getTimestamp()
        );
    }
}
