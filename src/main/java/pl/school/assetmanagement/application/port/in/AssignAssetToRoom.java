package pl.school.assetmanagement.application.port.in;

import pl.school.assetmanagement.domain.model.AssetId;
import pl.school.assetmanagement.domain.model.RoomId;

public interface AssignAssetToRoom {

    void assign(AssetId assetId, RoomId roomId);
}
