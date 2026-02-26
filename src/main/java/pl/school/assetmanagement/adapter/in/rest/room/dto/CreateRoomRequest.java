package pl.school.assetmanagement.adapter.in.rest.room.dto;

import pl.school.assetmanagement.domain.model.enums.RoomType;

public record CreateRoomRequest(

        String number,
        String description,
        RoomType roomType
) {
}
