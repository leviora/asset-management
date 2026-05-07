package pl.school.assetmanagement.adapter.out.persistence.activitylog;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.school.assetmanagement.domain.model.enums.ActivityType;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "activity_logs")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLogJpaEntity {

    @Id
    private UUID id;

    private Instant timestamp;

    @Enumerated(EnumType.STRING)
    private ActivityType type;

    private String assetId;
    private String fromRoom;
    private String toRoom;
    private String performedBy;
}