package pl.school.assetmanagement.application.port.out;

import pl.school.assetmanagement.domain.model.Room;
import pl.school.assetmanagement.domain.model.RoomId;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    Room save(Room room);
    Optional<Room> findById(RoomId id);
    List<Room> findAll();
    void deleteById(RoomId id);

    Optional<Room> findStorageRoom();
}
