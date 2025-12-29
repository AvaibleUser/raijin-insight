package edu.raijin.insight.fact.domain.port.persistence;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.fact.domain.model.EmployeeProductivityReport;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.model.StoryActivity;

@Port
public interface FindStoryActivityReportPort {

    Paged<EmployeeProductivityReport> findStoryActivityReport(Filter<StoryActivity> filter, Pageable pageable);
}
