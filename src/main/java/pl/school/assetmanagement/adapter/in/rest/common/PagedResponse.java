package pl.school.assetmanagement.adapter.in.rest.common;

import java.util.List;

import pl.school.assetmanagement.application.pagination.PageResult;

public record PagedResponse<T>(
        List<T> content,
        int number,
        int size,
        long totalElements,
        int totalPages
) {

    public static <T> PagedResponse<T> from(PageResult<T> page) {

        return new PagedResponse<>(
                page.content(),
                page.number(),
                page.size(),
                page.totalElements(),
                page.totalPages()
        );
    }
}
