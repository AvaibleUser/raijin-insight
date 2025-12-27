package edu.raijin.insight.fact.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.fact.domain.model.EmployeeEvent;

@Port
public interface RegisterEmployeeEventPort {

    Long register(Long eventId, EmployeeEvent employee);
}
