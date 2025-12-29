package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.impl;

import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.FinancialMovementSpecification.byAmount;
import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.FinancialMovementSpecification.byEmployee;
import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.FinancialMovementSpecification.byProject;
import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.FinancialMovementSpecification.byTransactionDateFrom;
import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.FinancialMovementSpecification.byTransactionDateTo;
import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.FinancialMovementSpecification.orderByClient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.model.FinancialMovement;
import edu.raijin.insight.fact.domain.model.FinancialReport;
import edu.raijin.insight.fact.domain.port.persistence.FindFinancialMovementReportPort;
import edu.raijin.insight.fact.domain.port.persistence.RegisterFinancialMovementPort;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.FinancialMovementsEntity;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.mapper.FinancialMovementEntityMapper;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.repository.JpaFinancialMovementRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class FinancialMovementRepositoryAdapter
        implements RegisterFinancialMovementPort, FindFinancialMovementReportPort {

    private final JpaFinancialMovementRepository movementRepository;
    private final FinancialMovementEntityMapper mapper;

    @Override
    public Long register(Long eventId, FinancialMovement movement) {
        FinancialMovementsEntity entity = mapper.toEntity(eventId, movement);
        if (entity.getProject().getProjectId() == null) {
            entity.setProject(null);
        }
        if (entity.getEmployee().getUserId() == null) {
            entity.setEmployee(null);
        }
        return movementRepository.save(entity).getId();
    }

    @Override
    public Paged<FinancialReport> findFinancialMovementReport(Filter<FinancialMovement> filter, Pageable pageable) {
        Specification<FinancialMovementsEntity> spec = byTransactionDateFrom(filter.getFrom())
                .and(byTransactionDateTo(filter.getTo()))
                .and(byProject(filter.getFilter().getProjectId()))
                .and(byEmployee(filter.getFilter().getEmployeeId()))
                .and(byAmount(filter.getFilter().getAmount()))
                .and(orderByClient());

        Page<FinancialReport> page = movementRepository.findAll(spec, pageable).map(mapper::toReport);
        return Paged.from(page);
    }
}
