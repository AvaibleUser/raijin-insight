package edu.raijin.insight.fact.domain.usecase;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.insight.fact.domain.model.EmployeeEvent;
import edu.raijin.insight.fact.domain.model.EmployeeReport;
import edu.raijin.insight.fact.domain.model.Filter;

@UseCase
public interface FetchEmployeeEventUseCase {

    Paged<EmployeeReport> fetch(Filter<EmployeeEvent> filter, Pageable pageable);
}
