package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.DatesEntity;

@Repository
public interface JpaDateRepository extends JpaRepository<DatesEntity, Long> {

}
