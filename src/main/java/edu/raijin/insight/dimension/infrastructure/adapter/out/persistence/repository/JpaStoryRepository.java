package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.StoriesEntity;

@Repository
public interface JpaStoryRepository extends JpaRepository<StoriesEntity, UUID> {

    Optional<StoriesEntity> findByStoryId(UUID id);

    boolean existsByStoryId(UUID id);
}

