package edu.raijin.insight.audit.infrastructure.adapter.out.persistence.impl;

import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.audit.domain.model.Snapshot;
import edu.raijin.insight.audit.domain.port.persistence.RegisterSnapshotPort;
import edu.raijin.insight.audit.infrastructure.adapter.out.persistence.entity.SnapshotsEntity;
import edu.raijin.insight.audit.infrastructure.adapter.out.persistence.mapper.SnapshotEntityMapper;
import edu.raijin.insight.audit.infrastructure.adapter.out.persistence.repository.JpaSnapshotRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class SnapshotRepositoryAdapter implements RegisterSnapshotPort {

    private final JpaSnapshotRepository snapshotRepository;
    private final SnapshotEntityMapper mapper;

    @Override
    public Long register(Snapshot snapshot) {
        SnapshotsEntity entity = mapper.toEntity(snapshot);
        return snapshotRepository.save(entity).getId();
    }
}
