package pl.school.assetmanagement.adapter.in.rest.assetModel;

import org.springframework.stereotype.Component;
import pl.school.assetmanagement.domain.model.AssetModelId;

import java.util.UUID;

@Component
public class AssetModelIdMapper {

    public String toString(AssetModelId id) {
        return id == null ? null : id.value().toString();
    }

    public AssetModelId toAssetModelId(String id) {
        return id == null ? null : new AssetModelId(UUID.fromString(id));
    }
}



