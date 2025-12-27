package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.model.Story;
import edu.raijin.insight.dimension.domain.port.persistence.RegisterStoryPort;
import edu.raijin.insight.dimension.domain.port.persistence.UpdateStoryPort;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.StoriesEntity;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.mapper.StoryEntityMapper;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.repository.JpaStoryRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class StoryRepositoryAdapter implements RegisterStoryPort, UpdateStoryPort {

    private final JpaStoryRepository storyRepository;
    private final StoryEntityMapper mapper;

    @Override
    public UUID register(Story story) {
        StoriesEntity entity = mapper.toEntity(story);
        return storyRepository.save(entity).getStoryId();
    }

    @Override
    public Story update(Story story) {
        StoriesEntity entity = mapper.toEntity(story);
        return mapper.toDomain(storyRepository.save(entity));
    }

    @Override
    public Optional<Story> findById(UUID id) {
        return storyRepository.findById(id)
                .map(mapper::toDomain);
    }
}

