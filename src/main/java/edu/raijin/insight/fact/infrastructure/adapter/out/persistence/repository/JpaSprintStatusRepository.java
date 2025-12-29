package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.SprintStatusEntity;

@Repository
public interface JpaSprintStatusRepository
        extends JpaRepository<SprintStatusEntity, Long>, JpaSpecificationExecutor<SprintStatusEntity> {

    Optional<SprintStatusEntity> findBySprintSprintId(UUID sprintId);
}
