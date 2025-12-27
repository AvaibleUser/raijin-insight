package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.model.Sprint;
import edu.raijin.insight.dimension.domain.port.persistence.RegisterSprintPort;
import edu.raijin.insight.dimension.domain.port.persistence.UpdateSprintPort;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.SprintsEntity;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.mapper.SprintEntityMapper;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.repository.JpaSprintRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class SprintRepositoryAdapter implements RegisterSprintPort, UpdateSprintPort {

    private final JpaSprintRepository sprintRepository;
    private final SprintEntityMapper mapper;

    @Override
    public UUID register(Sprint sprint) {
        SprintsEntity entity = mapper.toEntity(sprint);
        return sprintRepository.save(entity).getSprintId();
    }

    @Override
    public Sprint update(Sprint sprint) {
        SprintsEntity entity = mapper.toEntity(sprint);
        return mapper.toDomain(sprintRepository.save(entity));
    }

    @Override
    public Optional<Sprint> findById(UUID id) {
        return sprintRepository.findById(id)
                .map(mapper::toDomain);
    }
}

