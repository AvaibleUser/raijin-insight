package edu.raijin.insight.fact.domain.port.persistence;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.fact.domain.model.EmployeeEvent;
import edu.raijin.insight.fact.domain.model.EmployeeReport;
import edu.raijin.insight.fact.domain.model.Filter;

@Port
public interface FindEmployeeEventPort {

    Paged<EmployeeReport> find(Filter<EmployeeEvent> filter, Pageable pageable);
}
