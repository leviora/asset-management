package pl.school.assetmanagement.application.event.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import pl.school.assetmanagement.application.event.AssetActivityEvent;
import pl.school.assetmanagement.application.port.out.ActivityLogRepository;
import pl.school.assetmanagement.application.port.out.CurrentUserProvider;
import pl.school.assetmanagement.domain.model.ActivityLog;

@Component
@RequiredArgsConstructor
public class ActivityLogHandler {

    private final ActivityLogRepository repository;
    private final CurrentUserProvider currentUserProvider;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(AssetActivityEvent event) {

        ActivityLog log = ActivityLog.create(
                event.type(),
                event.assetId(),
                event.fromRoom(),
                event.toRoom(),
                currentUserProvider.currentUser()
        );

        repository.save(log);
    }
}
