package pl.school.assetmanagement.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Page<Asset> findByFilters(
            AssetStatus status,
            AssetType assetType,
            String serialNumber,
            Pageable pageable
    );
}
