package edu.raijin.insight.fact.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.insight.fact.domain.model.EmployeeEvent;

@UseCase
public interface CreateEmployeeEventUseCase {

    Long create(EmployeeEvent employeeEvent);
}
