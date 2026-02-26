package pl.school.assetmanagement.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.school.assetmanagement.application.port.in.GetAssetModels;
import pl.school.assetmanagement.application.port.out.AssetModelRepository;
import pl.school.assetmanagement.domain.model.AssetModel;
import pl.school.assetmanagement.domain.model.AssetModelId;
import pl.school.assetmanagement.exception.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAssetModelsService implements GetAssetModels {

    private final AssetModelRepository repository;

    @Override
    public List<AssetModel> getAll() {
        return repository.findAll();
    }

    @Override
    public AssetModel getById(String id) {
        return repository.findById(new AssetModelId(UUID.fromString(id)))
                .orElseThrow(() ->
                        new ResourceNotFoundException("AssetModel not found: " + id)
                );
    }

    @Override
    public List<AssetModel> searchByManufacturer(String manufacturer) {
        return repository.searchByManufacturer(manufacturer);
    }
}
