package pl.school.assetmanagement.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.school.assetmanagement.domain.model.enums.UserRole;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    final Long id;
    String firstName;
    String lastName;
    String email;
    UserRole role;
}
