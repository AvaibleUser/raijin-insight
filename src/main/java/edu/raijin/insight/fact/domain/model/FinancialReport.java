package edu.raijin.insight.fact.domain.model;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import edu.raijin.commons.domain.type.FinancialMovementCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Data
@With
@Builder
@Setter(NONE)
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class FinancialReport {

    private UUID projectId;

    private String projectName;

    private String client;

    private LocalDate projectStartDate;

    private LocalDate projectEndDate;

    private UUID employeeId;

    private String fullName;

    private String email;

    private String role;

    private Instant hiredAt;

    private Instant terminatedAt;

    private LocalDate transactionDate;

    private BigDecimal amount;

    private FinancialMovementCategory category;

    private String description;

    private BigDecimal subtotalAmount;

    private BigDecimal totalAmount;

    public static FinancialReport ofSubtotal(BigDecimal subtotalAmount) {
        return FinancialReport.builder()
                .subtotalAmount(subtotalAmount)
                .build();
    }

    public static FinancialReport ofTotal(BigDecimal totalAmount) {
        return FinancialReport.builder()
                .totalAmount(totalAmount)
                .build();
    }
}
