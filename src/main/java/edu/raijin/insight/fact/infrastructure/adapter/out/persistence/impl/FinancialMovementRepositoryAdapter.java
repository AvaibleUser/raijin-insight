package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.impl;

import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.FinancialMovement;
import edu.raijin.insight.fact.domain.port.persistence.RegisterFinancialMovementPort;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.FinancialMovementsEntity;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.mapper.FinancialMovementEntityMapper;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.repository.JpaFinancialMovementRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class FinancialMovementRepositoryAdapter implements RegisterFinancialMovementPort {

    private final JpaFinancialMovementRepository eventRepository;
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
        return eventRepository.save(entity).getId();
    }
}
