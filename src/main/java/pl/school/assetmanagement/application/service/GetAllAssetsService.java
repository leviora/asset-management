package pl.school.assetmanagement.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.school.assetmanagement.application.pagination.AppPageRequest;
import pl.school.assetmanagement.application.pagination.PageResult;
import pl.school.assetmanagement.application.port.in.GetAllAssets;
import pl.school.assetmanagement.application.port.out.AssetRepository;
import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.enums.AssetStatus;
import pl.school.assetmanagement.domain.model.enums.AssetType;

@Component
@RequiredArgsConstructor
public class GetAllAssetsService implements GetAllAssets {

    private final AssetRepository assetRepository;

    @Override
    public PageResult<Asset> getAll(
            AssetStatus status,
            AssetType assetType,
            String serialNumber,
            AppPageRequest pageRequest
    ) {
        return assetRepository.findByFilters(
                status,
                assetType,
                serialNumber,
                pageRequest
        );
    }
}
