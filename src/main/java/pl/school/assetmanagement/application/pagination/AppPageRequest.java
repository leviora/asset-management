package pl.school.assetmanagement.application.pagination;

import java.util.List;

public record AppPageRequest(
        int page,
        int size,
        List<AppSortOrder> sort
) {
}
