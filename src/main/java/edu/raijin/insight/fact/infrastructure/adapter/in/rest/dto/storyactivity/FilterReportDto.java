package edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.storyactivity;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public record FilterReportDto(
        Optional<LocalDate> from,
        Optional<LocalDate> to,
        Optional<UUID> projectId,
        Optional<UUID> developerId) {
}
