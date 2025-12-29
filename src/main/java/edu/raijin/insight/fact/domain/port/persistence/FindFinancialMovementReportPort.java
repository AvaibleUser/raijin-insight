package edu.raijin.insight.fact.domain.port.persistence;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.model.FinancialMovement;
import edu.raijin.insight.fact.domain.model.FinancialReport;

@Port
public interface FindFinancialMovementReportPort {

    Paged<FinancialReport> findFinancialMovementReport(Filter<FinancialMovement> filter, Pageable pageable);
}
