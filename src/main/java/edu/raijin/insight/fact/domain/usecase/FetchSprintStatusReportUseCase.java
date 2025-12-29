package edu.raijin.insight.fact.domain.usecase;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.model.ProjectAdvanceReport;
import edu.raijin.insight.fact.domain.model.SprintStatus;

@UseCase
public interface FetchSprintStatusReportUseCase {

    Paged<ProjectAdvanceReport> fetchSprintStatusReport(Filter<SprintStatus> filter, Pageable pageable);
}
