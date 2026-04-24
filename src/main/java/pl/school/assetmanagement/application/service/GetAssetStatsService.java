package pl.school.assetmanagement.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.school.assetmanagement.adapter.in.rest.asset.dto.AssetStatsResponse;
import pl.school.assetmanagement.application.port.in.GetAssetStats;
import pl.school.assetmanagement.application.port.out.AssetRepository;
import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.enums.AssetStatus;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAssetStatsService implements GetAssetStats {

    private final AssetRepository assetRepository;
    @Override
    public AssetStatsResponse getStats() {
        List<Asset> assets = assetRepository.findAll();

        long total = assets.size();

        long available = assets.stream()
                .filter(a -> a.getStatus() == AssetStatus.AVAILABLE)
                .count();

        long inUse = assets.stream()
                .filter(a -> a.getStatus() == AssetStatus.IN_USE)
                .count();

        long maintenance = assets.stream()
                .filter(a -> a.getStatus() == AssetStatus.MAINTENANCE)
                .count();

        long broken = assets.stream()
                .filter(a -> a.getStatus() == AssetStatus.BROKEN)
                .count();

        return new AssetStatsResponse(
                total,
                inUse,
                maintenance,
                available,
                broken
        );
    }
}
