package pl.school.assetmanagement.adapter.out.persistence.assetmodel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AssetModelJpaRepository
        extends JpaRepository<AssetModelJpaEntity, UUID> {

    boolean existsByManufacturerIgnoreCaseAndModelNameIgnoreCase(
            String manufacturer,
            String modelName
    );

    List<AssetModelJpaEntity> findByManufacturerContainingIgnoreCase(String manufacturer);
}
