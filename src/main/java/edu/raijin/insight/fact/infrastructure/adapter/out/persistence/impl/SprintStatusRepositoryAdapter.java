package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.SprintStatus;
import edu.raijin.insight.fact.domain.port.persistence.RegisterSprintStatusPort;
import edu.raijin.insight.fact.domain.port.persistence.UpdateSprintStatusPort;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.SprintStatusEntity;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.mapper.SprintStatusEntityMapper;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.repository.JpaSprintStatusRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class SprintStatusRepositoryAdapter implements RegisterSprintStatusPort, UpdateSprintStatusPort {

    private final JpaSprintStatusRepository eventRepository;
    private final SprintStatusEntityMapper mapper;

    @Override
    public Long register(Long fromDateId, Long toDateId, SprintStatus movement) {
        SprintStatusEntity entity = mapper.toEntity(fromDateId, toDateId, movement);
        return eventRepository.save(entity).getId();
    }

    @Override
    public Optional<SprintStatus> findBySprintId(UUID sprintId) {
        return eventRepository.findBySprintSprintId(sprintId).map(mapper::toDomain);
    }

    @Override
    public SprintStatus update(Long fromDateId, Long toDateId, SprintStatus sprintStatus) {
        SprintStatusEntity entity = mapper.toEntity(fromDateId, toDateId, sprintStatus);
        return mapper.toDomain(eventRepository.save(entity));
    }
}
