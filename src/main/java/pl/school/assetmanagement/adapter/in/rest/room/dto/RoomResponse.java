package pl.school.assetmanagement.adapter.in.rest.room.dto;

import pl.school.assetmanagement.domain.model.enums.RoomType;

import java.util.UUID;

public record RoomResponse(
        UUID roomId,
        String number,
        String description,
        RoomType roomType
) {
}
