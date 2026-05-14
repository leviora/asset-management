package pl.school.assetmanagement.adapter.out.mail;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import pl.school.assetmanagement.application.port.out.EmailSender;

@Component
@RequiredArgsConstructor
public class JavaMailEmailSender implements EmailSender {

    private final JavaMailSender mailSender;
    private final EmailTemplateService templateService;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendBrokenAssetNotification(
            String assetSerialNumber
    ) {

        try {

            String html = templateService.loadTemplate(
                    "broken-asset.html"
            );

            html = templateService.replacePlaceholder(
                    html,
                    "serialNumber",
                    assetSerialNumber
            );

            MimeMessage message =
                    mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            message,
                            true,
                            "UTF-8"
                    );

            helper.setFrom(from);

            helper.setTo(from);

            helper.setSubject(
                    "Broken asset detected"
            );

            helper.setText(html, true);

            mailSender.send(message);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to send email",
                    e
            );
        }
    }
}
