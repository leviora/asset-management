package pl.school.assetmanagement.application.event.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.school.assetmanagement.application.event.AssetActivityEvent;
import pl.school.assetmanagement.application.event.AssetAssignedToRoomEvent;
import pl.school.assetmanagement.application.port.out.ActivityLogRepository;
import pl.school.assetmanagement.domain.model.ActivityLog;
import pl.school.assetmanagement.domain.model.enums.ActivityType;

@Component
@RequiredArgsConstructor
public class ActivityLogHandler {

    private final ActivityLogRepository repository;

    @EventListener
    public void handle(AssetActivityEvent event) {

        ActivityLog log = ActivityLog.create(
                event.type(),
                event.assetId(),
                event.fromRoom(),
                event.toRoom(),
                "SYSTEM"
        );

        repository.save(log);
    }
}