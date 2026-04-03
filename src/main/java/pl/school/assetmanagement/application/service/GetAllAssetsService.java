package pl.school.assetmanagement.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.school.assetmanagement.application.port.in.GetAllAssets;
import pl.school.assetmanagement.application.port.out.AssetRepository;
import pl.school.assetmanagement.domain.model.Asset;

import java.util.List;
@Component
@RequiredArgsConstructor
public class GetAllAssetsService implements GetAllAssets {

    private final AssetRepository assetRepository;
    @Override
    public List<Asset> getAll() {
        return assetRepository.findAll();
    }
}
