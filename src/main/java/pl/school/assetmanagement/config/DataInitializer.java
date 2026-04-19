package pl.school.assetmanagement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.school.assetmanagement.application.port.in.CreateAsset;
import pl.school.assetmanagement.application.port.out.AssetModelRepository;
import pl.school.assetmanagement.application.port.out.AssetRepository;
import pl.school.assetmanagement.application.port.out.RoomRepository;
import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.AssetId;
import pl.school.assetmanagement.domain.model.AssetModel;
import pl.school.assetmanagement.domain.model.Room;
import pl.school.assetmanagement.domain.model.enums.AssetStatus;
import pl.school.assetmanagement.domain.model.enums.AssetType;
import pl.school.assetmanagement.domain.model.enums.RoomType;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final AssetModelRepository assetModelRepository;
    private final AssetRepository assetRepository;
    private final RoomRepository roomRepository;

    @Bean
    CommandLineRunner initData() {
        return args -> {

            if (!assetModelRepository.findAll().isEmpty()) {
                return;
            }

            // --- ROOMS ---
            Room room101 = roomRepository.save(
                    Room.create("101", "Computer room", RoomType.COMPUTER_LAB)
            );

            Room room117 = roomRepository.save(
                    Room.create("117", "Lecture room", RoomType.LECTURE)
            );

            Room room119 = roomRepository.save(
                    Room.create("119", "Computer room", RoomType.COMPUTER_LAB)
            );

            Room storage = roomRepository.save(
                    Room.create("108", "Storage room", RoomType.STORAGE)
            );

            // --- MODELS ---
            AssetModel mac = assetModelRepository.save(
                    AssetModel.createNew("Apple", "MacBook Pro M2", AssetType.COMPUTER)
            );

            AssetModel dell = assetModelRepository.save(
                    AssetModel.createNew("Dell", "OptiPlex 7000", AssetType.COMPUTER)
            );

            AssetModel projector = assetModelRepository.save(
                    AssetModel.createNew("Epson", "Projector X200", AssetType.PROJECTOR)
            );



            assetRepository.save(Asset.restore(
                    AssetId.newAssetId(),
                    mac.getId(),
                    AssetType.COMPUTER,
                    AssetStatus.AVAILABLE,
                    "SN-MAC-001",
                    storage.getRoomId()
            ));

            assetRepository.save(Asset.restore(
                    AssetId.newAssetId(),
                    mac.getId(),
                    AssetType.COMPUTER,
                    AssetStatus.IN_USE,
                    "SN-MAC-002",
                    room101.getRoomId()
            ));

            assetRepository.save(Asset.restore(
                    AssetId.newAssetId(),
                    mac.getId(),
                    AssetType.COMPUTER,
                    AssetStatus.MAINTENANCE,
                    "SN-MAC-003",
                    storage.getRoomId()
            ));

            assetRepository.save(Asset.restore(
                    AssetId.newAssetId(),
                    dell.getId(),
                    AssetType.COMPUTER,
                    AssetStatus.AVAILABLE,
                    "SN-DELL-001",
                    storage.getRoomId()
            ));

            assetRepository.save(Asset.restore(
                    AssetId.newAssetId(),
                    dell.getId(),
                    AssetType.COMPUTER,
                    AssetStatus.IN_USE,
                    "SN-DELL-002",
                    room119.getRoomId()
            ));

            assetRepository.save(Asset.restore(
                    AssetId.newAssetId(),
                    projector.getId(),
                    AssetType.PROJECTOR,
                    AssetStatus.MAINTENANCE,
                    "SN-PROJ-001",
                    storage.getRoomId()
            ));
        };
    }
}
