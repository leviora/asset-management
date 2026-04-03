package pl.school.assetmanagement.application.port.in;

import pl.school.assetmanagement.domain.model.Asset;

import java.util.List;

public interface GetAllAssets {

    List<Asset> getAll();
}
