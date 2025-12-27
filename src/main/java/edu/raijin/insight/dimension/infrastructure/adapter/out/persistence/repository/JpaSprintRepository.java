package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.SprintsEntity;

@Repository
public interface JpaSprintRepository extends JpaRepository<SprintsEntity, UUID> {

    Optional<SprintsEntity> findBySprintId(UUID id);

    boolean existsBySprintId(UUID id);
}

