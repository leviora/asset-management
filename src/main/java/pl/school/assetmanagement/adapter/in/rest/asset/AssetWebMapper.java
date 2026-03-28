package pl.school.assetmanagement.adapter.in.rest.asset;

import org.springframework.stereotype.Component;
import pl.school.assetmanagement.adapter.in.rest.asset.dto.AssetResponse;
import pl.school.assetmanagement.domain.model.Asset;

@Component
public class AssetWebMapper {

    public AssetResponse toResponse(Asset asset) {
        return new AssetResponse(
                asset.getId().value(),
                asset.getAssetModelId().value(),
                asset.getStatus().name(),
                asset.getSerialNumber(),
                asset.getRoomId() != null ? asset.getRoomId().value() : null,
                asset.getAssetType()
        );
    }
}
