package edu.raijin.insight.fact.application.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.insight.fact.domain.model.EmployeeProductivityReport;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.model.StoryActivity;
import edu.raijin.insight.fact.domain.port.persistence.FindStoryActivityReportPort;
import edu.raijin.insight.fact.domain.usecase.FetchStoryActivityReportUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchStoryActivityService implements FetchStoryActivityReportUseCase {

    private final FindStoryActivityReportPort find;

    @Override
    @Transactional
    public Paged<EmployeeProductivityReport> fetchStoryActivityReport(Filter<StoryActivity> filter, Pageable pageable) {
        Paged<EmployeeProductivityReport> page = find.findStoryActivityReport(filter, pageable);
        List<EmployeeProductivityReport> items = page.getItems();
        List<EmployeeProductivityReport> result = new ArrayList<>();

        UUID lastUser = null;
        int subtotalStories = 0;
        int subtotalPoints = 0;
        BigDecimal subtotalHours = BigDecimal.ZERO;
        for (EmployeeProductivityReport item : items) {
            UUID currentUser = item.getDeveloper().getUserId();
            if (lastUser != null && !Objects.equals(currentUser, lastUser)) {
                result.add(EmployeeProductivityReport.builder()
                        .hoursSpent(subtotalHours)
                        .pointsDone(subtotalPoints)
                        .totalStories(subtotalStories)
                        .build());
                subtotalStories = 0;
                subtotalHours = BigDecimal.ZERO;
                subtotalPoints = 0;
            }
            result.add(item);
            lastUser = currentUser;
            subtotalStories++;
            subtotalPoints += item.getStory().getPoints();
            subtotalHours = subtotalHours.add(item.getHoursSpent());
        }
        if (subtotalStories > 0) {
            result.add(EmployeeProductivityReport.builder()
                    .hoursSpent(subtotalHours)
                    .pointsDone(subtotalPoints)
                    .totalStories(subtotalStories)
                    .build());
        }
        page.setItems(result);
        return page;
    }
}
