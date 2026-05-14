package pl.school.assetmanagement.application.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import pl.school.assetmanagement.application.event.AssetActivityEvent;
import pl.school.assetmanagement.application.port.out.EmailSender;
import pl.school.assetmanagement.domain.model.enums.ActivityType;

@Slf4j
@Component
@RequiredArgsConstructor
public class AssetNotificationListener {

    private final EmailSender emailSender;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleAssetActivity(AssetActivityEvent event) {

        if (event.type() == ActivityType.MARKED_AS_BROKEN) {

            try {

                emailSender.sendBrokenAssetNotification(
                        event.serialNumber()
                );

            } catch (Exception e) {

                log.error(
                        "Failed to send broken asset email for asset {}",
                        event.assetId(),
                        e
                );
            }
        }
    }
}
