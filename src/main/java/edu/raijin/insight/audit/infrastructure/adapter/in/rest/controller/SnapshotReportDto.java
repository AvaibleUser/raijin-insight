package edu.raijin.insight.audit.infrastructure.adapter.in.rest.controller;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record SnapshotReportDto(
        Long id,
        String eventType,
        UUID aggregateId,
        UUID actorId,
        String fullName,
        String email,
        String role,
        Instant occurredAt,
        String description,
        Map<String, Object> details) {
}
