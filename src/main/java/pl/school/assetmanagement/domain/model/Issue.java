package pl.school.assetmanagement.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.school.assetmanagement.domain.model.enums.IssueStatus;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Issue {

    final Long id;
    final Long assetId;
    final Long reportedByUserId;
    String description;
    IssueStatus status;
    final LocalDateTime dateReported;
    LocalDateTime dateFixed;
}
