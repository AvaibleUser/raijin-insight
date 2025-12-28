package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.StoryActivity;
import edu.raijin.insight.fact.domain.port.persistence.RegisterStoryActivityPort;
import edu.raijin.insight.fact.domain.port.persistence.UpdateStoryActivityPort;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.StoryActivityEntity;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.mapper.StoryActivityEntityMapper;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.repository.JpaStoryActivityRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class StoryActivityRepositoryAdapter implements RegisterStoryActivityPort, UpdateStoryActivityPort {

    private final JpaStoryActivityRepository eventRepository;
    private final StoryActivityEntityMapper mapper;

    @Override
    public Long register(Long fromDateId, StoryActivity movement) {
        StoryActivityEntity entity = mapper.toEntity(fromDateId, movement);
        return eventRepository.save(entity).getId();
    }

    @Override
    public Optional<StoryActivity> findByStoryId(UUID storyId) {
        return eventRepository.findByStoryStoryId(storyId).map(mapper::toDomain);
    }

    @Override
    public StoryActivity update(Long fromDateId, Long toDateId, StoryActivity storyActivity) {
        StoryActivityEntity entity = mapper.toEntity(fromDateId, toDateId, storyActivity);
        return mapper.toDomain(eventRepository.save(entity));
    }
}
