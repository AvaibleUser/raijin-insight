package edu.raijin.insight.dimension.infrastructure.adapter.in.rest.dto.user;

import java.time.Instant;
import java.util.UUID;

public record RoleReportDto(
        UUID employeeId,
        String fullName,
        String email,
        String role,
        Instant hiredAt,
        Instant terminatedAt,
        Integer subtotal,
        Integer total) {
}
