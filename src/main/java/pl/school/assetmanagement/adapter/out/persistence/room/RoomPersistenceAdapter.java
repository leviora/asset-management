package pl.school.assetmanagement.adapter.out.persistence.room;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.school.assetmanagement.domain.model.Room;
import pl.school.assetmanagement.application.port.out.RoomRepository;
import pl.school.assetmanagement.domain.model.RoomId;
import pl.school.assetmanagement.domain.model.enums.RoomType;

import java.util.List;
import java.util.Optional;
@Repository
@AllArgsConstructor
public class RoomPersistenceAdapter implements RoomRepository {


    private final SpringRoomJpaRepository jpaRepository;

    @Override
    public Room save(Room room) {
        RoomJpaEntity entity = mapToEntity(room);
        RoomJpaEntity saved = jpaRepository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<Room> findById(RoomId id) {
        return jpaRepository.findById(id.value())
                .map(this::mapToDomain);
    }

    @Override
    public List<Room> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::mapToDomain)
                .toList();
    }

    @Override
    public void deleteById(RoomId id) {
        jpaRepository.deleteById(id.value());
    }

    private RoomJpaEntity mapToEntity(Room room) {
        return new RoomJpaEntity(
                room.getNumber(),
                room.getRoomType()
        );
    }

    private Room mapToDomain(RoomJpaEntity entity) {
        return new Room(
                new RoomId(entity.getId()),
                entity.getNumber(),
                null,
                entity.getRoomType()
        );
    }

    @Override
    public Optional<Room> findStorageRoom() {
        return jpaRepository.findByRoomType(RoomType.STORAGE)
                .map(this::mapToDomain);
    }


}
