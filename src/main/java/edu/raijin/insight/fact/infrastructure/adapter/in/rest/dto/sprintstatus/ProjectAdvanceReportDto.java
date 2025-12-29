package edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.sprintstatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import edu.raijin.commons.domain.type.SprintStatus;

public record ProjectAdvanceReportDto(
        UUID projectId,
        String projectName,
        String client,
        LocalDate projectStartDate,
        LocalDate projectEndDate,
        UUID sprintId,
        String sprintName,
        SprintStatus sprintStatus,
        LocalDate sprintStartDate,
        LocalDate sprintEndDate,
        Integer pointsDone,
        Integer pointsPlanned,
        BigDecimal percentDone,
        List<Member> members) {

    public static record Member(
            UUID id,
            String fullName,
            String email,
            String role) {
    }
}
