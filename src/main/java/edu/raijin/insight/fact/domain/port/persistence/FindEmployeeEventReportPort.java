package edu.raijin.insight.fact.domain.port.persistence;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.fact.domain.model.EmployeeEvent;
import edu.raijin.insight.fact.domain.model.EmployeeReport;
import edu.raijin.insight.fact.domain.model.Filter;

@Port
public interface FindEmployeeEventReportPort {

    Paged<EmployeeReport> findEventsReport(Filter<EmployeeEvent> filter, Pageable pageable);
}
