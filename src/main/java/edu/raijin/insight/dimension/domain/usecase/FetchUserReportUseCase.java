package edu.raijin.insight.dimension.domain.usecase;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.insight.dimension.domain.model.RoleReport;
import edu.raijin.insight.dimension.domain.model.User;
import edu.raijin.insight.fact.domain.model.Filter;

@UseCase
public interface FetchUserReportUseCase {

    Paged<RoleReport> fetchRoleReport(Filter<User> filter, Pageable pageable);

}
