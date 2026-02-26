package pl.school.assetmanagement.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.school.assetmanagement.domain.model.Room;
import pl.school.assetmanagement.application.port.out.RoomRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public Room createRoom(Room room) {

        validateRoom(room);
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    private void validateRoom(Room room) {
        if(room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        if(room.getNumber() == null || room.getNumber().isBlank()) {
            throw new IllegalArgumentException("Room not found");
        }
        if(room.getRoomType() == null) {
            throw new IllegalArgumentException("Room type is required");
        }
    }
}
