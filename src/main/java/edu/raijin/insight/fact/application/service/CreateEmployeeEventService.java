package edu.raijin.insight.fact.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.insight.dimension.domain.usecase.CreateDateUseCase;
import edu.raijin.insight.fact.domain.model.EmployeeEvent;
import edu.raijin.insight.fact.domain.port.persistence.RegisterEmployeeEventPort;
import edu.raijin.insight.fact.domain.usecase.CreateEmployeeEventUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateEmployeeEventService implements CreateEmployeeEventUseCase {

    private final RegisterEmployeeEventPort register;
    private final CreateDateUseCase createDate;

    @Override
    @Transactional
    public Long create(EmployeeEvent employeeEvent) {
        Long dateId = createDate.create(employeeEvent.getEventDate());
        employeeEvent.checkValidRegistration();
        return register.register(dateId, employeeEvent);
    }
}
