package pl.school.assetmanagement.application.pagination;

public record AppSortOrder(
        String property,
        AppSortDirection direction
) {
}
