package pl.school.assetmanagement.application.port.in;

import pl.school.assetmanagement.domain.model.Room;

import java.util.List;

public interface GetRooms {

    List<Room> getAll();
}
