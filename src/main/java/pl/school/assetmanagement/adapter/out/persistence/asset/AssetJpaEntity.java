package pl.school.assetmanagement.adapter.out.persistence.asset;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.school.assetmanagement.domain.model.enums.AssetStatus;
import pl.school.assetmanagement.domain.model.enums.AssetType;

import java.util.UUID;

@Entity
@Table(name = "assets")
@Getter
@Setter
@NoArgsConstructor
public class AssetJpaEntity {

    @Id
    private UUID id;

    private UUID assetModelId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetStatus status;

    private String serialNumber;

    private UUID roomId;

    @Enumerated(EnumType.STRING)
    private AssetType assetType;

}