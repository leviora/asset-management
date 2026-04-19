package pl.school.assetmanagement.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pl.school.assetmanagement.application.port.in.GetAllAssets;
import pl.school.assetmanagement.application.port.out.AssetRepository;
import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.enums.AssetStatus;
import pl.school.assetmanagement.domain.model.enums.AssetType;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllAssetsService implements GetAllAssets {

    private final AssetRepository assetRepository;

    @Override
    public Page<Asset> getAll(
            AssetStatus status,
            AssetType assetType,
            String serialNumber,
            Pageable pageable
    ) {
        return assetRepository.findByFilters(
                status,
                assetType,
                serialNumber,
                pageable
        );
    }
}