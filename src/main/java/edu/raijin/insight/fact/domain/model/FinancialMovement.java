package edu.raijin.insight.fact.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import edu.raijin.commons.domain.type.FinancialMovementCategory;
import edu.raijin.commons.util.exception.BadRequestException;
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
public class FinancialMovement {

    private Long id;

    private UUID projectId;

    private UUID employeeId;

    private LocalDate transactionDate;

    private BigDecimal amount;

    private FinancialMovementCategory category;

    private String description;

    public void checkValidRegistration() {
        requireNonNull(transactionDate, () -> new BadRequestException("La fecha de la transacción es requerida"));
        requireNonNull(amount, () -> new BadRequestException("El monto de la transacción es requerido"));
        requireNonNull(category, () -> new BadRequestException("La categoría de la transacción es requerida"));
    }
}
