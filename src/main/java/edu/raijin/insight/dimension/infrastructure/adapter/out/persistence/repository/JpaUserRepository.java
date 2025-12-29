package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.UsersEntity;

@Repository
public interface JpaUserRepository extends JpaRepository<UsersEntity, UUID>, JpaSpecificationExecutor<UsersEntity> {

    Optional<UsersEntity> findByUserId(UUID id);

    boolean existsByUserId(UUID id);
}
