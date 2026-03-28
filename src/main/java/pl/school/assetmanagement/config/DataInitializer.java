package pl.school.assetmanagement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.school.assetmanagement.application.port.in.CreateAsset;
import pl.school.assetmanagement.application.port.out.AssetModelRepository;
import pl.school.assetmanagement.domain.model.AssetModel;
import pl.school.assetmanagement.domain.model.AssetModelId;
import pl.school.assetmanagement.domain.model.enums.AssetType;

import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final AssetModelRepository assetModelRepository;
    private final CreateAsset createAsset;
    @Bean
    CommandLineRunner initData() {
        return args -> {

            if (!assetModelRepository.findAll().isEmpty()) {
                return;
            }

            AssetModel mac = assetModelRepository.save(
                    AssetModel.createNew("Apple", "MacBook Pro M2", AssetType.COMPUTER)
            );

            AssetModel dell = assetModelRepository.save(
                    AssetModel.createNew("Dell", "OptiPlex 7000", AssetType.COMPUTER)
            );

            AssetModel projector = assetModelRepository.save(
                    AssetModel.createNew("Epson", "Projector X200", AssetType.PROJECTOR)
            );

            createAsset.create(mac.getId(), "SN-MAC-001", AssetType.COMPUTER);
            createAsset.create(mac.getId(), "SN-MAC-002", AssetType.COMPUTER);
            createAsset.create(dell.getId(), "SN-DELL-001", AssetType.PROJECTOR);
            createAsset.create(projector.getId(), "SN-PROJ-001", AssetType.KEYBOARD);
            System.out.println("🔥 AssetModels + Assets initialized");
        };
    }
}
