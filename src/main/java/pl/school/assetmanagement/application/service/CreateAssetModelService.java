package pl.school.assetmanagement.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.school.assetmanagement.application.port.in.CreateAssetModel;
import pl.school.assetmanagement.application.port.out.AssetModelRepository;
import pl.school.assetmanagement.domain.model.AssetModel;
import pl.school.assetmanagement.domain.model.enums.AssetType;
import pl.school.assetmanagement.exception.AssetModelAlreadyExistsException;

@Service
@RequiredArgsConstructor
public class CreateAssetModelService implements CreateAssetModel {

    private final AssetModelRepository assetModelRepository;

    @Override
    public AssetModel create(String manufacturer,
                             String modelName,
                             AssetType assetType) {

        String normalizedManufacturer = manufacturer.trim();
        String normalizedModelName = modelName.trim();

        boolean exists = assetModelRepository
                .existsByManufacturerIgnoreCaseAndModelNameIgnoreCase(
                        normalizedManufacturer,
                        normalizedModelName
                );

        if (exists) {
            throw new AssetModelAlreadyExistsException(
                    normalizedManufacturer,
                    normalizedModelName
            );
        }

        AssetModel assetModel = AssetModel.createNew(
                normalizedManufacturer,
                normalizedModelName,
                assetType
        );

        return assetModelRepository.save(assetModel);
    }

}
