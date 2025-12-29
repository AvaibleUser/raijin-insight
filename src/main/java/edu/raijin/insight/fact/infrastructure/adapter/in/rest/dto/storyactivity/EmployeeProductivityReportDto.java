package edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.storyactivity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import edu.raijin.commons.domain.type.SprintStatus;
import edu.raijin.commons.domain.type.StoryPriority;

public record EmployeeProductivityReportDto(
        Project project,
        Story story,
        Sprint sprint,
        User productOwner,
        User developer,
        LocalDate fromDate,
        LocalDate toDate,
        BigDecimal hoursSpent,
        Integer stageChanges,
        Integer totalStories,
        Integer pointsDone) {

    public static record Project(
            UUID id,
            String name,
            String client,
            LocalDate startDate,
            LocalDate endDate) {
    }

    public static record Sprint(
            UUID id,
            String name,
            SprintStatus status,
            LocalDate startDate,
            LocalDate endDate) {
    }

    public static record User(
            UUID id,
            String fullName,
            String email,
            String role,
            Instant hiredAt,
            Instant terminatedAt) {
    }

    public static record Story(
            UUID id,
            String name,
            String stage,
            Integer points,
            StoryPriority priority) {
    }
}
