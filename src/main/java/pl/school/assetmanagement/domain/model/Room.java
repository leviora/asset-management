package pl.school.assetmanagement.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import pl.school.assetmanagement.domain.model.enums.RoomType;

@Getter
@AllArgsConstructor
public class Room {

    private final RoomId roomId;
    private String number;
    private String description;
    private RoomType roomType;

    public static Room create(String number, String description, RoomType roomType) {
        return new Room(RoomId.newId(), number, description, roomType);
    }

    public static Room restore(RoomId roomId, String number, String description, RoomType roomType) {
        return new Room(roomId, number, description, roomType);
    }

    public void rename(String newNumber) {
        if (newNumber == null || newNumber.isBlank())
            throw new IllegalArgumentException("Room number cannot be empty");
        this.number = newNumber;
    }

    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    public void changeRoomType(RoomType newType) {
        if (newType == null)
            throw new IllegalArgumentException("RoomType cannot be null");
        this.roomType = newType;
    }
}
