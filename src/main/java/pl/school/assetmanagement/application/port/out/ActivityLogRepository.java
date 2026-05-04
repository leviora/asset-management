package pl.school.assetmanagement.application.port.out;

import pl.school.assetmanagement.domain.model.ActivityLog;

import java.util.List;

public interface ActivityLogRepository {

    void save(ActivityLog log);

    List<ActivityLog> findToday();
}
