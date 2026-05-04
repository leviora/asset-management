package pl.school.assetmanagement.domain.model;

import java.util.UUID;

public record ActivityLogId(UUID value) {

    public static ActivityLogId newId() {
        return new ActivityLogId(UUID.randomUUID());
    }
}
