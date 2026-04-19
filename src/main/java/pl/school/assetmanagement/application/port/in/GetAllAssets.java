package pl.school.assetmanagement.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.enums.AssetStatus;
import pl.school.assetmanagement.domain.model.enums.AssetType;

import java.util.List;

public interface GetAllAssets {

    Page<Asset> getAll(
            AssetStatus status,
            AssetType assetType,
            String serialNumber,
            Pageable pageable
    );
}
