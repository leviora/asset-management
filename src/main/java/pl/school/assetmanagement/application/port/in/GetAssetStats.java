package pl.school.assetmanagement.application.port.in;

import pl.school.assetmanagement.adapter.in.rest.asset.dto.AssetStatsResponse;

public interface GetAssetStats {

    AssetStatsResponse getStats();
}
