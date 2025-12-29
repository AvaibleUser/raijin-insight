package edu.raijin.insight.fact.domain.usecase;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.model.FinancialMovement;
import edu.raijin.insight.fact.domain.model.FinancialReport;

@UseCase
public interface FetchFinancialMovementReportUseCase {

    Paged<FinancialReport> fetchFinancialProjectReport(Filter<FinancialMovement> filter, Pageable pageable);
}
