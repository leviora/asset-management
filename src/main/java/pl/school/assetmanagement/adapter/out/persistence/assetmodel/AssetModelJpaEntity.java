package pl.school.assetmanagement.adapter.out.persistence.assetmodel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.school.assetmanagement.domain.model.enums.AssetType;

import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class AssetModelJpaEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    private String manufacturer;

    private String modelName;

    @Enumerated(EnumType.STRING)
    private AssetType assetType;
}
