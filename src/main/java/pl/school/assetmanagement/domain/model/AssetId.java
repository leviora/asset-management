package pl.school.assetmanagement.domain.model;

import java.util.UUID;

public record AssetId(UUID value) {


    public static AssetId newAssetId() {
        return new AssetId(UUID.randomUUID());
    }
}
