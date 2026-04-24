package pl.school.assetmanagement.adapter.in.rest.asset.dto;

public record AssetStatsResponse(
        long total,
        long inUse,
        long maintenance,
        long available,
        long broken
) {
}
