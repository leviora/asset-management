package pl.school.assetmanagement.adapter.out.persistence.asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface AssetJpaRepository
        extends JpaRepository<AssetJpaEntity, UUID>,
        JpaSpecificationExecutor<AssetJpaEntity> {
}