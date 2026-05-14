package pl.school.assetmanagement.application.port.in;

import pl.school.assetmanagement.domain.model.ActivityLog;

import java.util.List;

public interface GetTodayActivityLogs {

    List<ActivityLog> getToday();
}
