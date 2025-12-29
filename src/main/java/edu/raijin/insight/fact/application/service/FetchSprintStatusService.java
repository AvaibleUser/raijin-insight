package edu.raijin.insight.fact.application.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.model.ProjectAdvanceReport;
import edu.raijin.insight.fact.domain.model.SprintStatus;
import edu.raijin.insight.fact.domain.port.persistence.FindSprintStatusReportPort;
import edu.raijin.insight.fact.domain.usecase.FetchSprintStatusReportUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchSprintStatusService implements FetchSprintStatusReportUseCase {

    private final FindSprintStatusReportPort find;

    @Override
    @Transactional
    public Paged<ProjectAdvanceReport> fetchSprintStatusReport(Filter<SprintStatus> filter, Pageable pageable) {
        return find.findSprintStatusReport(filter, pageable);
    }
}
