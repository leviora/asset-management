package pl.school.assetmanagement.application.event.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.school.assetmanagement.application.event.AssetAssignedToRoomEvent;
import pl.school.assetmanagement.application.port.out.ActivityLogRepository;
import pl.school.assetmanagement.domain.model.ActivityLog;
import pl.school.assetmanagement.domain.model.enums.ActivityType;

@Component
@RequiredArgsConstructor
public class ActivityLogHandler {

    private final ActivityLogRepository repository;

    @EventListener
    public void handle(AssetAssignedToRoomEvent event) {

        System.out.println("🔥 EVENT RECEIVED"); // debug

        ActivityLog log = ActivityLog.create(
                ActivityType.ASSIGNED_TO_ROOM,
                event.getAssetId(),
                event.getPreviousRoom(),
                event.getNewRoom(),
                "SYSTEM"
        );

        repository.save(log);
    }
}