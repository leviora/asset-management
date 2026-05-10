package pl.school.assetmanagement.application.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.school.assetmanagement.application.event.AssetActivityEvent;
import pl.school.assetmanagement.application.service.email.EmailService;
import pl.school.assetmanagement.domain.model.enums.ActivityType;

@Slf4j
@Component
@RequiredArgsConstructor
public class AssetNotificationListener {

    private final EmailService emailService;

    @EventListener
    public void handleAssetActivity(AssetActivityEvent event) {

        if (event.type() == ActivityType.MARKED_AS_BROKEN) {

            try {

                emailService.sendBrokenAssetNotification(
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
