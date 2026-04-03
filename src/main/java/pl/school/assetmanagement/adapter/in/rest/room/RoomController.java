package pl.school.assetmanagement.adapter.in.rest.room;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.school.assetmanagement.adapter.in.rest.room.dto.RoomResponse;
import pl.school.assetmanagement.application.port.out.RoomRepository;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomRepository roomRepository;

    @GetMapping
    public List<RoomResponse> getAll() {
        return roomRepository.findAll()
                .stream()
                .map(r -> new RoomResponse(
                        r.getRoomId().value(),
                        r.getNumber(),
                        r.getDescription(),
                        r.getRoomType()
                ))
                .toList();
    }
}





