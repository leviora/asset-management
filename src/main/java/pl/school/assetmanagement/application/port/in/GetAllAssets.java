package pl.school.assetmanagement.application.port.in;

import pl.school.assetmanagement.application.pagination.AppPageRequest;
import pl.school.assetmanagement.application.pagination.PageResult;
import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.enums.AssetStatus;
import pl.school.assetmanagement.domain.model.enums.AssetType;

public interface GetAllAssets {

    PageResult<Asset> getAll(
            AssetStatus status,
            AssetType assetType,
            String serialNumber,
            AppPageRequest pageRequest
    );
}
