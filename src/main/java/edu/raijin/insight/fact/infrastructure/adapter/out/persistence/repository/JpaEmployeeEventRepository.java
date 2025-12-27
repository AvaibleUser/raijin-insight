package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.EmployeeEventsEntity;

@Repository
public interface JpaEmployeeEventRepository extends JpaRepository<EmployeeEventsEntity, Long> {

}
