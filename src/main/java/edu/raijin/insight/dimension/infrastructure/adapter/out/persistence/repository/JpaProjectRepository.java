package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.ProjectsEntity;

@Repository
public interface JpaProjectRepository extends JpaRepository<ProjectsEntity, UUID> {

    Optional<ProjectsEntity> findByProjectId(UUID id);

    boolean existsByProjectId(UUID id);

    boolean existsByProjectIdAndMembersUserId(UUID projectId, UUID userId);
}
