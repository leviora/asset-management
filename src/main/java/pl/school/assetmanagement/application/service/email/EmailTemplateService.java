package pl.school.assetmanagement.application.service.email;

import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class EmailTemplateService {

    @SneakyThrows
    public String loadTemplate(String templateName) {

        ClassPathResource resource =
                new ClassPathResource(
                        "email-templates/" + templateName
                );

        byte[] bytes = resource
                .getInputStream()
                .readAllBytes();

        return new String(bytes, StandardCharsets.UTF_8);
    }

    public String replacePlaceholder(
            String template,
            String placeholder,
            String value
    ) {

        return template.replace(
                "{{" + placeholder + "}}",
                value
        );
    }
}