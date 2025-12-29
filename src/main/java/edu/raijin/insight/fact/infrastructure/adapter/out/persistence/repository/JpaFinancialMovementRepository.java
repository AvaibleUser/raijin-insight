package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.FinancialMovementsEntity;

@Repository
public interface JpaFinancialMovementRepository
        extends JpaRepository<FinancialMovementsEntity, Long>, JpaSpecificationExecutor<FinancialMovementsEntity> {

}
