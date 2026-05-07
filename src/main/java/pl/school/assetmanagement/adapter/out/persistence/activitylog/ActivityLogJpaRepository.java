package pl.school.assetmanagement.adapter.out.persistence.activitylog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface ActivityLogJpaRepository extends JpaRepository<ActivityLogJpaEntity, UUID> {

    List<ActivityLogJpaEntity> findByTimestampBetween(Instant start, Instant end);
}