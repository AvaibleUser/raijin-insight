package edu.raijin.insight.dimension.domain.port.persistence;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.dimension.domain.model.RoleReport;
import edu.raijin.insight.dimension.domain.model.User;
import edu.raijin.insight.fact.domain.model.Filter;

@Port
public interface FindUserReportPort {

    Paged<RoleReport> fetchRoleReport(Filter<User> filter, Pageable pageable);
}
