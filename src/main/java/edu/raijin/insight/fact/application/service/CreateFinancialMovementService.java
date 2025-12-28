package edu.raijin.insight.fact.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.insight.dimension.domain.usecase.CreateDateUseCase;
import edu.raijin.insight.fact.domain.model.FinancialMovement;
import edu.raijin.insight.fact.domain.port.persistence.RegisterFinancialMovementPort;
import edu.raijin.insight.fact.domain.usecase.CreateFinancialMovementUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateFinancialMovementService implements CreateFinancialMovementUseCase {

    private final RegisterFinancialMovementPort register;
    private final CreateDateUseCase createDate;

    @Override
    @Transactional
    public Long create(FinancialMovement financialMovement) {
        Long dateId = createDate.create(financialMovement.getTransactionDate());
        financialMovement.checkValidRegistration();
        return register.register(dateId, financialMovement);
    }
}
