package edu.raijin.insight.fact.domain.usecase;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.insight.fact.domain.model.EmployeeProductivityReport;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.model.StoryActivity;

@UseCase
public interface FetchStoryActivityReportUseCase {

    Paged<EmployeeProductivityReport> fetchStoryActivityReport(Filter<StoryActivity> filter, Pageable pageable);
}
