package edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.sprintstatus;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public record FilterReportDto(
        Optional<LocalDate> from,
        Optional<LocalDate> to,
        Optional<UUID> projectId) {
}
