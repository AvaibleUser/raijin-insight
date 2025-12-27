package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.DatesEntity;

@Repository
public interface JpaDateRepository extends JpaRepository<DatesEntity, Long> {

    Optional<DatesEntity> findByDate(LocalDate date);
}
