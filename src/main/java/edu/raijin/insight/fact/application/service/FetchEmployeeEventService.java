package edu.raijin.insight.fact.application.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.insight.fact.domain.model.EmployeeEvent;
import edu.raijin.insight.fact.domain.model.EmployeeReport;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.port.persistence.FindEmployeeEventReportPort;
import edu.raijin.insight.fact.domain.usecase.FetchEmployeeEventReportUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchEmployeeEventService implements FetchEmployeeEventReportUseCase {

    private final FindEmployeeEventReportPort find;

    @Override
    @Transactional
    public Paged<EmployeeReport> fetchEventsReport(Filter<EmployeeEvent> filter, Pageable pageable) {
        return find.findEventsReport(filter, pageable);
    }
}
