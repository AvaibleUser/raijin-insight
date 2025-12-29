package edu.raijin.insight.dimension.infrastructure.adapter.in.rest.dto.user;

import java.time.LocalDate;
import java.util.Optional;

public record FilterReportDto(
        Optional<LocalDate> from,
        Optional<LocalDate> to,
        Optional<String> role) {
}
