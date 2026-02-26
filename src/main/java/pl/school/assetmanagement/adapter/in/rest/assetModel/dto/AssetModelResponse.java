package pl.school.assetmanagement.adapter.in.rest.assetModel.dto;

import pl.school.assetmanagement.domain.model.enums.AssetType;

import java.util.UUID;

public record AssetModelResponse(
        UUID id,
        String manufacturer,
        String modelName,
        AssetType assetType
) {
}
