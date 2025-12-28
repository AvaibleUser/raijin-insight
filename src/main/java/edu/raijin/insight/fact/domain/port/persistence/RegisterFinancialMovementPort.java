package edu.raijin.insight.fact.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.fact.domain.model.FinancialMovement;

@Port
public interface RegisterFinancialMovementPort {

    Long register(Long eventId, FinancialMovement movement);
}
