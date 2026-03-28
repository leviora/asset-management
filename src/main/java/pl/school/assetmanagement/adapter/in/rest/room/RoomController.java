//package pl.school.assetmanagement.adapter.in.rest.room;
//
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import pl.school.assetmanagement.adapter.in.rest.room.dto.CreateRoomRequest;
//import pl.school.assetmanagement.adapter.in.rest.room.dto.RoomResponse;
//import pl.school.assetmanagement.domain.model.Room;
//import pl.school.assetmanagement.application.service.RoomService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/rooms")
//@AllArgsConstructor
//public class RoomController {
//
//    private final RoomService roomService;
//    private final RoomWebMapper roomWebMapper;
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<RoomResponse> getAllRooms() {
//        return roomWebMapper.toResponseList(
//                roomService.getAllRooms()
//        );
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public RoomResponse createRoom(@RequestBody CreateRoomRequest request) {
//        Room room = roomWebMapper.toDomain(request);
//        Room saved = roomService.createRoom(room);
//        return roomWebMapper.toResponse(saved);
//    }
//
//    @GetMapping("/{id}")
//    public RoomResponse getRoomById(@PathVariable Long id) {
//        return roomWebMapper.toResponse(
//                roomService.getRoomById(id)
//        );
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteRoom(@PathVariable Long id) {
//        roomService.deleteRoom(id);
//    }
//}
//
//
