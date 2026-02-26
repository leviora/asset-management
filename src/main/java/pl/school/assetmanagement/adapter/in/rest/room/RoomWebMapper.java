package pl.school.assetmanagement.adapter.in.rest.room;

import org.mapstruct.Mapper;
import pl.school.assetmanagement.adapter.in.rest.room.dto.CreateRoomRequest;
import pl.school.assetmanagement.adapter.in.rest.room.dto.RoomResponse;
import pl.school.assetmanagement.domain.model.Room;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomWebMapper {

    Room toDomain(CreateRoomRequest request);

    RoomResponse toResponse(Room room);

    List<RoomResponse> toResponseList(List<Room> rooms);
}
