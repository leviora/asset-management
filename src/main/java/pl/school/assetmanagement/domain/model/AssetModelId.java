package pl.school.assetmanagement.domain.model;

import java.util.UUID;

public record AssetModelId(UUID value) {

    public static AssetModelId newId() {
        return new AssetModelId(UUID.randomUUID());
    }
}
