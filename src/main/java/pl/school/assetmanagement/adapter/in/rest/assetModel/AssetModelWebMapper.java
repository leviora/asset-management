package pl.school.assetmanagement.adapter.in.rest.assetModel;

import org.mapstruct.Mapper;
import pl.school.assetmanagement.adapter.in.rest.assetModel.dto.AssetModelResponse;
import pl.school.assetmanagement.domain.model.AssetModel;

@Mapper(componentModel = "spring", uses = AssetModelIdMapper.class)
public interface AssetModelWebMapper {

    AssetModelResponse toResponse(AssetModel assetModel);
}
