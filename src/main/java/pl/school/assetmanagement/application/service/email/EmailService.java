package pl.school.assetmanagement.application.service.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendBrokenAssetNotification(String assetSerialNumber) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);

        message.setTo(from);

        message.setSubject("Broken asset detected");

        message.setText(
                "Asset with serial number "
                        + assetSerialNumber
                        + " has been marked as BROKEN."
        );

        mailSender.send(message);
    }
}
