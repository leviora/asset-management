package pl.school.assetmanagement.config;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.notifications.email")
public class NotificationProperties {

    private List<String> recipients = new ArrayList<>();
}
