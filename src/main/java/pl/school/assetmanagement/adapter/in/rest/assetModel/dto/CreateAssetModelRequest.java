package pl.school.assetmanagement.adapter.in.rest.assetModel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pl.school.assetmanagement.domain.model.enums.AssetType;

public record CreateAssetModelRequest(
        @NotBlank(message = "Manufacturer must not be blank")
        String manufacturer,
        @NotBlank(message = "Model name must not be blank")
        String modelName,
        @NotNull(message = "Asset type must not be null")
        AssetType assetType
) {
}
