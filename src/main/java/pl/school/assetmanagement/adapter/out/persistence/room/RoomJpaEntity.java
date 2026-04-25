package pl.school.assetmanagement.adapter.out.persistence.room;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import pl.school.assetmanagement.domain.model.enums.RoomType;

import java.util.UUID;

@Entity
@Table(name = "rooms")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomJpaEntity {

    @Id
    @GeneratedValue
    UUID id;

    @Column(nullable = false, unique = true)
    String number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    RoomType roomType;

    protected RoomJpaEntity() {

    }

    public RoomJpaEntity(String name, RoomType roomType) {
        this.number = name;
        this.roomType = roomType;
    }

}
