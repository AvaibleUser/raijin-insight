package edu.raijin.insight.fact.domain.port.persistence;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.model.ProjectAdvanceReport;
import edu.raijin.insight.fact.domain.model.SprintStatus;

@Port
public interface FindSprintStatusReportPort {

    Paged<ProjectAdvanceReport> findSprintStatusReport(Filter<SprintStatus> filter, Pageable pageable);
}
