package pl.school.assetmanagement.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.school.assetmanagement.domain.model.enums.AssetStatus;
import pl.school.assetmanagement.domain.model.enums.AssetType;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Asset {

    final Long id;
    final String name;
    final AssetType type;
    final String serialNumber;
    AssetStatus status;
    Long roomId;
    final String description;
}
