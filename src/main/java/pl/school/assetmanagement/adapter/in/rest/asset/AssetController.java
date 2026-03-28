package pl.school.assetmanagement.adapter.in.rest.asset;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.school.assetmanagement.adapter.in.rest.asset.dto.AssetResponse;
import pl.school.assetmanagement.adapter.in.rest.asset.dto.CreateAssetRequest;
import pl.school.assetmanagement.adapter.out.persistence.assetmodel.AssetModelJpaRepository;
import pl.school.assetmanagement.application.port.in.*;
import pl.school.assetmanagement.domain.model.AssetId;
import pl.school.assetmanagement.domain.model.AssetModelId;

import java.util.UUID;

@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class AssetController {

    private final CreateAsset createAsset;
    private final MarkAssetAsBroken markAssetAsBroken;
    private final SendAssetToMaintenance sendAssetToMaintenance;
    private final RemoveAssetFromRoom removeAssetFromRoom;
    private final GetAsset getAsset;
    private final AssetWebMapper webMapper;
    private final AssetModelJpaRepository assetModelJpaRepository;


    @PostMapping
    public ResponseEntity<UUID> create(@RequestBody CreateAssetRequest request) {

        AssetId id = createAsset.create(
                new AssetModelId(request.assetModelId()),
                request.serialNumber(),
                request.assetType()
        );

        return ResponseEntity.ok(id.value());
    }

    @PatchMapping("/{id}/broken")
    public ResponseEntity<Void> markAsBroken(@PathVariable UUID id) {

        markAssetAsBroken.mark(new AssetId(id));

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/maintenance")
    public ResponseEntity<Void> sendToMaintenance(@PathVariable UUID id) {

        sendAssetToMaintenance.send(new AssetId(id));

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/remove-room")
    public ResponseEntity<Void> removeFromRoom(@PathVariable UUID id) {

        removeAssetFromRoom.remove(new AssetId(id));

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetResponse> get(@PathVariable UUID id) {

        var asset = getAsset.get(new AssetId(id));

        return ResponseEntity.ok(webMapper.toResponse(asset));
    }
}
