package pl.school.assetmanagement.adapter.in.rest.assetModel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.school.assetmanagement.adapter.in.rest.assetModel.dto.AssetModelResponse;
import pl.school.assetmanagement.adapter.in.rest.assetModel.dto.CreateAssetModelRequest;
import pl.school.assetmanagement.application.port.in.CreateAssetModel;
import pl.school.assetmanagement.application.port.in.GetAssetModels;
import pl.school.assetmanagement.domain.model.AssetModel;

import java.util.List;

@RestController
@RequestMapping("/api/asset-models")
@RequiredArgsConstructor
public class AssetModelController {

    private final CreateAssetModel createAssetModel;
    private final AssetModelWebMapper mapper;
    private final GetAssetModels getAssetModels;

    @PostMapping
    public ResponseEntity<AssetModelResponse> create(
            @RequestBody @Valid CreateAssetModelRequest request
    ) {

        AssetModel model = createAssetModel.create(
                request.manufacturer(),
                request.modelName(),
                request.assetType()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(model));
    }

    @GetMapping
    public ResponseEntity<List<AssetModelResponse>> getAll() {
        List<AssetModelResponse> response = getAssetModels.getAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetModelResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(
                mapper.toResponse(getAssetModels.getById(id))
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<AssetModelResponse>> search(
            @RequestParam String manufacturer
    ) {
        List<AssetModelResponse> response =
                getAssetModels.searchByManufacturer(manufacturer)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }
}