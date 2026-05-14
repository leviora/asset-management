package pl.school.assetmanagement.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.school.assetmanagement.application.port.in.GetRooms;
import pl.school.assetmanagement.application.port.out.RoomRepository;
import pl.school.assetmanagement.domain.model.Room;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRoomsService implements GetRooms {

    private final RoomRepository roomRepository;

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }
}
