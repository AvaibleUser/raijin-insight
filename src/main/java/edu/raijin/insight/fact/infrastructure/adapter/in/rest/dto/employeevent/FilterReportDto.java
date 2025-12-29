package edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.employeevent;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.domain.type.EmployeeEventType;

public record FilterReportDto(
        Optional<LocalDate> from,
        Optional<LocalDate> to,
        Optional<EmployeeEventType> eventType,
        Optional<UUID> employeeId) {
}
