package edu.raijin.insight.audit.infrastructure.adapter.in.rest.dto.snapshot;

import java.time.LocalDate;
import java.util.Optional;

public record FilterReportDto(
        Optional<LocalDate> from,
        Optional<LocalDate> to) {
}
