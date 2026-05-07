package pl.school.assetmanagement.adapter.out.persistence.activitylog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.school.assetmanagement.application.port.out.ActivityLogRepository;
import pl.school.assetmanagement.domain.model.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ActivityLogPersistenceAdapter implements ActivityLogRepository {

    private final ActivityLogJpaRepository jpaRepository;

    @Override
    public void save(ActivityLog log) {

        ActivityLogJpaEntity entity = new ActivityLogJpaEntity(
                log.getId().value(),
                log.getTimestamp(),
                log.getType(),
                log.getAssetId().value().toString(),
                log.getFromRoom() != null ? log.getFromRoom().value().toString() : null,
                log.getToRoom() != null ? log.getToRoom().value().toString() : null,
                log.getPerformedBy()
        );

        jpaRepository.save(entity);
    }

    @Override
    public List<ActivityLog> findToday() {

        Instant now = Instant.now();

        Instant start = now
                .atZone(ZoneId.of("UTC"))
                .toLocalDate()
                .atStartOfDay(ZoneId.of("UTC"))
                .toInstant();

        Instant end = start.plus(1, ChronoUnit.DAYS);

        return jpaRepository.findByTimestampBetween(start, end)
                .stream()
                .map(e -> ActivityLog.restore(
                        new ActivityLogId(e.getId()),
                        e.getTimestamp(),
                        e.getType(),
                        new AssetId(UUID.fromString(e.getAssetId())),
                        e.getFromRoom() != null ? new RoomId(UUID.fromString(e.getFromRoom())) : null,
                        e.getToRoom() != null ? new RoomId(UUID.fromString(e.getToRoom())) : null,
                        e.getPerformedBy()
                ))
                .toList();
    }
}