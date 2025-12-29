package edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.employeevent;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import edu.raijin.commons.domain.type.EmployeeEventType;

public record EmployeeReportDto(
        UUID employeeId,
        String fullName,
        String email,
        String role,
        Instant hiredAt,
        Instant terminatedAt,
        LocalDate eventDate,
        EmployeeEventType eventType) {
}
