package pl.school.assetmanagement.adapter.out.user;

import org.springframework.stereotype.Component;
import pl.school.assetmanagement.application.port.out.CurrentUserProvider;

@Component
public class SystemCurrentUserProvider implements CurrentUserProvider {

    @Override
    public String currentUser() {

        return "SYSTEM";
    }
}
