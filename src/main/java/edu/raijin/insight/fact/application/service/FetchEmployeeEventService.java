package edu.raijin.insight.fact.application.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.insight.fact.domain.model.EmployeeEvent;
import edu.raijin.insight.fact.domain.model.EmployeeReport;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.port.persistence.FindEmployeeEventPort;
import edu.raijin.insight.fact.domain.usecase.FetchEmployeeEventUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchEmployeeEventService implements FetchEmployeeEventUseCase {

    private final FindEmployeeEventPort find;

    @Override
    public Paged<EmployeeReport> fetch(Filter<EmployeeEvent> filter, Pageable pageable) {
        return find.find(filter, pageable);
    }
}
