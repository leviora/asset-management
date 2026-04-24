package pl.school.assetmanagement.adapter.in.rest.asset;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.school.assetmanagement.adapter.in.rest.asset.dto.AssetResponse;
import pl.school.assetmanagement.adapter.in.rest.asset.dto.AssetStatsResponse;
import pl.school.assetmanagement.adapter.in.rest.asset.dto.CreateAssetRequest;
import pl.school.assetmanagement.application.port.in.*;
import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.AssetId;
import pl.school.assetmanagement.domain.model.AssetModelId;
import pl.school.assetmanagement.domain.model.RoomId;
import pl.school.assetmanagement.domain.model.enums.AssetStatus;
import pl.school.assetmanagement.domain.model.enums.AssetType;

import java.util.List;
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
    private final GetAllAssets getAllAssets;
    private final AssignAssetToRoom assignAssetToRoom;
    private final MarkAssetAvailable markAssetAvailable;
    private final GetAssetStats getAssetStats;

    @GetMapping
    public ResponseEntity<Page<AssetResponse>> getAll(
            @RequestParam(required = false) AssetStatus status,
            @RequestParam(required = false) AssetType assetType,
            @RequestParam(required = false) String serialNumber,
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                getAllAssets.getAll(
                        status,
                        assetType,
                        serialNumber,
                        pageable
                ).map(webMapper::toResponse)
        );
    }

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

        Asset asset = getAsset.get(new AssetId(id));

        return ResponseEntity.ok(webMapper.toResponse(asset));
    }

    @PatchMapping("/{id}/assign-room/{roomId}")
    public ResponseEntity<Void> assignToRoom(@PathVariable UUID id, @PathVariable UUID roomId) {
        assignAssetToRoom.assign(new AssetId(id), new RoomId(roomId));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/available")
    public ResponseEntity<Void> markAvailable(@PathVariable UUID id) {
        markAssetAvailable.mark(new AssetId(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats")
    public ResponseEntity<AssetStatsResponse> getStats() {
        return ResponseEntity.ok(getAssetStats.getStats());
    }
}
