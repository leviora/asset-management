package pl.school.assetmanagement.application.port.out;

import pl.school.assetmanagement.application.pagination.AppPageRequest;
import pl.school.assetmanagement.application.pagination.PageResult;
import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.AssetId;
import pl.school.assetmanagement.domain.model.enums.AssetStatus;
import pl.school.assetmanagement.domain.model.enums.AssetType;

import java.util.List;
import java.util.Optional;

public interface AssetRepository {

    Asset save(Asset asset);

    Optional<Asset> findById(AssetId id);

    List<Asset> findAll();

    PageResult<Asset> findByFilters(
            AssetStatus status,
            AssetType assetType,
            String serialNumber,
            AppPageRequest pageRequest
    );
}
