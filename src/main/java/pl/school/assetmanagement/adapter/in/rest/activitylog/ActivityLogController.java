package pl.school.assetmanagement.adapter.in.rest.activitylog;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.school.assetmanagement.adapter.in.rest.activitylog.dto.ActivityLogResponse;
import pl.school.assetmanagement.application.port.out.ActivityLogRepository;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class ActivityLogController {

    private final ActivityLogRepository activityLogRepository;
    private final ActivityLogWebMapper mapper;

    @GetMapping("/today")
    public ResponseEntity<List<ActivityLogResponse>> getTodayLogs() {

        List<ActivityLogResponse> response = activityLogRepository.findToday()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }
}
