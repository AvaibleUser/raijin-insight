package edu.raijin.insight.audit.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import edu.raijin.insight.audit.infrastructure.adapter.out.persistence.entity.SnapshotsEntity;

@Repository
public interface JpaSnapshotRepository
        extends JpaRepository<SnapshotsEntity, Long>, JpaSpecificationExecutor<SnapshotsEntity> {

}
