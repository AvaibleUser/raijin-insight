package edu.raijin.insight.fact.infrastructure.adapter.in.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.domain.type.ExpenseType;
import edu.raijin.commons.domain.type.FinancialMovementCategory;
import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.BonusEvent;
import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.DiscountEvent;
import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.ExpenseEvent;
import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.IncomeEvent;
import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.PayrollEvent;
import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.SuspensionEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.FinancialMovement;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FinancialEventMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "amount", expression = "java(event.getAmount().negate())")
    @Mapping(target = "description", source = "reason")
    @Mapping(target = "category", constant = "BONUS")
    FinancialMovement toDomain(BonusEvent event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "description", source = "reason")
    @Mapping(target = "category", constant = "DISCOUNT")
    FinancialMovement toDomain(DiscountEvent event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "amount", expression = "java(event.getAmount().negate())")
    @Mapping(target = "transactionDate", source = "expenseDate")
    @Mapping(target = "category", source = "type")
    FinancialMovement toDomain(ExpenseEvent event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "transactionDate", source = "billingDate")
    @Mapping(target = "category", constant = "INCOME")
    FinancialMovement toDomain(IncomeEvent event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "amount", expression = "java(event.getBaseSalary().negate())")
    @Mapping(target = "description", constant = "Pago del sueldo base")
    @Mapping(target = "transactionDate", source = "paymentDate")
    @Mapping(target = "category", constant = "DISCOUNT")
    FinancialMovement toDomain(PayrollEvent event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "description", source = "reason")
    @Mapping(target = "category", constant = "DISCOUNT")
    FinancialMovement toDomain(SuspensionEvent event);

    default FinancialMovementCategory toCategory(ExpenseType type) {
        return switch (type) {
            case OPERATIONAL -> FinancialMovementCategory.OPERATIONAL_EXPENSE;
            case SALARY -> FinancialMovementCategory.SALARY;
            case OTHER -> FinancialMovementCategory.OTHER_EXPENSE;
        };
    }
}
