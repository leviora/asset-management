package pl.school.assetmanagement.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import pl.school.assetmanagement.domain.model.enums.RoomType;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Room {

    final Long id;
    String number;
    String description;
    RoomType roomType;

}
