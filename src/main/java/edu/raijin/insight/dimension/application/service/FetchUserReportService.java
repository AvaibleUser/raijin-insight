package edu.raijin.insight.dimension.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.insight.dimension.domain.model.RoleReport;
import edu.raijin.insight.dimension.domain.model.User;
import edu.raijin.insight.dimension.domain.port.persistence.FindUserReportPort;
import edu.raijin.insight.dimension.domain.usecase.FetchUserReportUseCase;
import edu.raijin.insight.fact.domain.model.Filter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchUserReportService implements FetchUserReportUseCase {

    private final FindUserReportPort find;

    @Override
    @Transactional
    public Paged<RoleReport> fetchRoleReport(Filter<User> filter, Pageable pageable) {
        Paged<RoleReport> page = find.fetchRoleReport(filter, pageable);
        List<RoleReport> items = page.getItems();
        List<RoleReport> result = new ArrayList<>();

        String lastGroup = null;
        int subtotal = 0;
        for (RoleReport item : items) {
            String currentGroup = item.getRole();
            if (lastGroup != null && !Objects.equals(currentGroup, lastGroup)) {
                result.add(RoleReport.ofSubtotal(subtotal));
                subtotal = 0;
            }
            result.add(item);
            lastGroup = currentGroup;
            subtotal++;
        }
        if (subtotal > 0) {
            result.add(RoleReport.ofSubtotal(subtotal));
        }
        result.add(RoleReport.ofTotal(items.size()));
        page.setItems(result);
        return page;
    }
}
