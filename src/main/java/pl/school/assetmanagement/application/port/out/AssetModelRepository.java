package pl.school.assetmanagement.application.port.out;

import pl.school.assetmanagement.domain.model.AssetModel;
import pl.school.assetmanagement.domain.model.AssetModelId;

import java.util.List;
import java.util.Optional;

public interface AssetModelRepository {

    AssetModel save(AssetModel assetModel);

    List<AssetModel> findAll();

    Optional<AssetModel> findById(AssetModelId id);

    boolean existsByManufacturerIgnoreCaseAndModelNameIgnoreCase(
            String manufacturer,
            String modelName
    );

    List<AssetModel> searchByManufacturer(String manufacturerPart);


}
