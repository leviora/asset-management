package pl.school.assetmanagement.adapter.out.persistence.assetmodel;

import org.springframework.stereotype.Component;
import pl.school.assetmanagement.domain.model.AssetModelId;

import java.util.UUID;

@Component
public class AssetModelIdJpaMapper {

    public UUID toUuid(AssetModelId id) {
        return id == null ? null : id.value();
    }

    public AssetModelId toAssetModelId(UUID id) {
        return id == null ? null : new AssetModelId(id);
    }
}
