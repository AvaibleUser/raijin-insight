package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.StoryActivityEntity;

@Repository
public interface JpaStoryActivityRepository
        extends JpaRepository<StoryActivityEntity, Long>, JpaSpecificationExecutor<StoryActivityEntity> {

    Optional<StoryActivityEntity> findByStoryStoryId(UUID storyId);
}
