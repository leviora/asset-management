package pl.school.assetmanagement.adapter.out.persistence.asset;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import pl.school.assetmanagement.application.pagination.AppPageRequest;
import pl.school.assetmanagement.application.pagination.AppSortDirection;
import pl.school.assetmanagement.application.pagination.AppSortOrder;
import pl.school.assetmanagement.application.pagination.PageResult;
import pl.school.assetmanagement.application.port.out.AssetRepository;
import pl.school.assetmanagement.domain.model.Asset;
import pl.school.assetmanagement.domain.model.AssetId;
import pl.school.assetmanagement.domain.model.enums.AssetStatus;
import pl.school.assetmanagement.domain.model.enums.AssetType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AssetPersistenceAdapter implements AssetRepository {

    private final AssetJpaRepository jpaRepository;
    private final AssetJpaMapper mapper;

    @Override
    public PageResult<Asset> findByFilters(
            AssetStatus status,
            AssetType assetType,
            String serialNumber,
            AppPageRequest pageRequest
    ) {
        Page<Asset> page = jpaRepository.findAll((root, query, cb) -> {
                    List<Predicate> predicates = new ArrayList<>();

                    if (status != null) {
                        predicates.add(
                                cb.equal(root.get("status"), status)
                        );
                    }

                    if (assetType != null) {
                        predicates.add(
                                cb.equal(root.get("assetType"), assetType)
                        );
                    }

                    if (serialNumber != null && !serialNumber.isBlank()) {
                        predicates.add(
                                cb.like(
                                        cb.lower(root.get("serialNumber")),
                                        "%" + serialNumber.toLowerCase() + "%"
                                )
                        );
                    }

                    return cb.and(predicates.toArray(new Predicate[0]));
                }, toPageable(pageRequest))
                .map(mapper::toDomain);

        return new PageResult<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    @Override
    public Asset save(Asset asset) {
        AssetJpaEntity entity = mapper.toEntity(asset);
        AssetJpaEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Asset> findById(AssetId id) {
        return jpaRepository.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public List<Asset> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    private Pageable toPageable(AppPageRequest pageRequest) {

        return PageRequest.of(
                pageRequest.page(),
                pageRequest.size(),
                toSort(pageRequest.sort())
        );
    }

    private Sort toSort(List<AppSortOrder> sort) {

        if (sort == null || sort.isEmpty()) {
            return Sort.unsorted();
        }

        return Sort.by(
                sort.stream()
                        .map(order -> new Sort.Order(
                                toDirection(order.direction()),
                                order.property()
                        ))
                        .toList()
        );
    }

    private Sort.Direction toDirection(AppSortDirection direction) {

        if (direction == AppSortDirection.DESC) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }
}

