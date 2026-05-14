package pl.school.assetmanagement.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.school.assetmanagement.application.port.in.GetTodayActivityLogs;
import pl.school.assetmanagement.application.port.out.ActivityLogRepository;
import pl.school.assetmanagement.domain.model.ActivityLog;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTodayActivityLogsService implements GetTodayActivityLogs {

    private final ActivityLogRepository activityLogRepository;

    @Override
    public List<ActivityLog> getToday() {
        return activityLogRepository.findToday();
    }
}
