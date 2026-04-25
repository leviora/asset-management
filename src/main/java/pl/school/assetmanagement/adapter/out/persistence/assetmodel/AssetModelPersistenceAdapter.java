package pl.school.assetmanagement.adapter.out.persistence.assetmodel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.school.assetmanagement.application.port.out.AssetModelRepository;
import pl.school.assetmanagement.domain.model.AssetModel;
import pl.school.assetmanagement.domain.model.AssetModelId;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AssetModelPersistenceAdapter implements AssetModelRepository {

    private final AssetModelJpaRepository repository;
    private final AssetModelJpaMapper mapper;

    @Override
    public List<AssetModel> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<AssetModel> findById(AssetModelId id) {
        return repository.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByManufacturerIgnoreCaseAndModelNameIgnoreCase(
            String manufacturer,
            String modelName
    ) {
        return repository.existsByManufacturerIgnoreCaseAndModelNameIgnoreCase(
                manufacturer,
                modelName
        );
    }

    @Override
    public List<AssetModel> searchByManufacturer(String manufacturerPart) {
        return repository.findByManufacturerContainingIgnoreCase(manufacturerPart)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public AssetModel save(AssetModel assetModel) {
        AssetModelJpaEntity entity = mapper.toEntity(assetModel);
        AssetModelJpaEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }
}