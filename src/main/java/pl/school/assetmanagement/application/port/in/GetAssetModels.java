package pl.school.assetmanagement.application.port.in;

import pl.school.assetmanagement.domain.model.AssetModel;

import java.util.List;

public interface GetAssetModels {

    List<AssetModel> getAll();

    AssetModel getById(String id);

    List<AssetModel> searchByManufacturer(String manufacturer);
}
