package edu.raijin.insight.audit.infrastructure.adapter.out.persistence.impl;

import static edu.raijin.insight.audit.infrastructure.adapter.out.persistence.specification.SnapshotSpecification.byOccurredAtFrom;
import static edu.raijin.insight.audit.infrastructure.adapter.out.persistence.specification.SnapshotSpecification.byOccurredAtTo;
import static edu.raijin.insight.audit.infrastructure.adapter.out.persistence.specification.SnapshotSpecification.orderByOccurredAt;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.audit.domain.model.Snapshot;
import edu.raijin.insight.audit.domain.port.persistence.FindSnapshotReportPort;
import edu.raijin.insight.audit.domain.port.persistence.RegisterSnapshotPort;
import edu.raijin.insight.audit.infrastructure.adapter.out.persistence.entity.SnapshotsEntity;
import edu.raijin.insight.audit.infrastructure.adapter.out.persistence.mapper.SnapshotEntityMapper;
import edu.raijin.insight.audit.infrastructure.adapter.out.persistence.repository.JpaSnapshotRepository;
import edu.raijin.insight.fact.domain.model.Filter;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class SnapshotRepositoryAdapter implements RegisterSnapshotPort, FindSnapshotReportPort {

    private final JpaSnapshotRepository snapshotRepository;
    private final SnapshotEntityMapper mapper;

    @Override
    public Long register(Snapshot snapshot) {
        SnapshotsEntity entity = mapper.toEntity(snapshot);
        return snapshotRepository.save(entity).getId();
    }

    @Override
    public Paged<Snapshot> find(Filter<Snapshot> filter, Pageable pageable) {
        Specification<SnapshotsEntity> spec = byOccurredAtFrom(filter.getFrom())
                .and(byOccurredAtTo(filter.getTo()))
                .and(orderByOccurredAt());

        Page<Snapshot> page = snapshotRepository.findAll(spec, pageable).map(mapper::toDomain);
        return Paged.from(page);
    }
}
