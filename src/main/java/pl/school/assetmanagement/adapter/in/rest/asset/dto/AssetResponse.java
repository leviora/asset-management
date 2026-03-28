package pl.school.assetmanagement.adapter.in.rest.asset.dto;

import pl.school.assetmanagement.domain.model.enums.AssetType;

import java.util.UUID;

public record AssetResponse(
        UUID id,
        UUID assetModelId,
        String status,
        String serialNumber,
        UUID roomId,
        AssetType assetType
) {
}
