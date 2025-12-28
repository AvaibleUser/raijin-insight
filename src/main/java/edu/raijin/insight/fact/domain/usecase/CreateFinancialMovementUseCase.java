package edu.raijin.insight.fact.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.insight.fact.domain.model.FinancialMovement;

@UseCase
public interface CreateFinancialMovementUseCase {

    Long create(FinancialMovement movement);
}
