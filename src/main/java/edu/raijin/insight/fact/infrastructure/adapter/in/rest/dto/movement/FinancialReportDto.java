package edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.movement;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import edu.raijin.commons.domain.type.FinancialMovementCategory;

public record FinancialReportDto(
        UUID projectId,
        String projectName,
        String client,
        LocalDate projectStartDate,
        LocalDate projectEndDate,
        UUID employeeId,
        String fullName,
        String email,
        String role,
        Instant hiredAt,
        Instant terminatedAt,
        LocalDate transactionDate,
        BigDecimal amount,
        FinancialMovementCategory category,
        String description,
        BigDecimal subtotalAmount,
        BigDecimal totalAmount) {
}
