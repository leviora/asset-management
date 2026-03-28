package pl.school.assetmanagement.adapter.in.rest.asset.dto;

import jakarta.validation.constraints.NotNull;
import pl.school.assetmanagement.domain.model.enums.AssetType;

import java.util.UUID;

public record CreateAssetRequest(

        @NotNull(message = "assetModelId is required")
        UUID assetModelId,
        String serialNumber,
        AssetType assetType

) {
}