package pl.school.assetmanagement.adapter.out.persistence.room;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.school.assetmanagement.domain.model.Room;
import pl.school.assetmanagement.domain.model.enums.RoomType;

import java.util.Optional;
import java.util.UUID;

public interface SpringRoomJpaRepository
extends JpaRepository<RoomJpaEntity, UUID> {

    Optional<RoomJpaEntity> findByRoomType(RoomType roomType);
}
