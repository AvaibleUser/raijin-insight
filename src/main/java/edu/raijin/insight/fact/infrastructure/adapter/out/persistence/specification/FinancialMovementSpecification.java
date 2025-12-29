package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification;

import static jakarta.persistence.criteria.JoinType.LEFT;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.domain.Specification;

import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.FinancialMovementsEntity;
import jakarta.persistence.criteria.Path;

public interface FinancialMovementSpecification {

    static Specification<FinancialMovementsEntity> byTransactionDateFrom(LocalDate from) {
        if (from == null) {
            return Specification.unrestricted();
        }
        return (root, query, builder) -> {
            Path<LocalDate> date = root.join("transactionDate", LEFT).get("date");
            return query
                    .where(builder.greaterThanOrEqualTo(date, from))
                    .groupBy(root.get("id"), date)
                    .getRestriction();
        };
    }

    static Specification<FinancialMovementsEntity> byTransactionDateTo(LocalDate to) {
        if (to == null) {
            return Specification.unrestricted();
        }
        return (root, query, builder) -> {
            Path<LocalDate> date = root.join("transactionDate", LEFT).get("date");
            return query
                    .where(builder.lessThanOrEqualTo(date, to))
                    .groupBy(root.get("id"), date)
                    .getRestriction();
        };
    }

    static PredicateSpecification<FinancialMovementsEntity> byProject(UUID projectId) {
        if (projectId == null) {
            return PredicateSpecification.unrestricted();
        }
        return (from, builder) -> builder.equal(from.join("project", LEFT).get("projectId"), projectId);
    }

    static PredicateSpecification<FinancialMovementsEntity> byEmployee(UUID employeeId) {
        if (employeeId == null) {
            return PredicateSpecification.unrestricted();
        }
        return (from, builder) -> builder.equal(from.join("employee", LEFT).get("userId"), employeeId);
    }

    static PredicateSpecification<FinancialMovementsEntity> byAmount(BigDecimal amount) {
        if (amount == null) {
            return PredicateSpecification.unrestricted();
        }
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            return (from, builder) -> builder.gt(from.get("amount"), 0);
        }
        return (from, builder) -> builder.lt(from.get("amount"), 0);
    }

    static Specification<FinancialMovementsEntity> orderByClient() {
        return (root, query, builder) -> {
            Path<Object> project = root.join("project", LEFT).get("projectId");
            Path<Object> employee = root.join("employee", LEFT).get("userId");
            Path<Object> date = root.join("transactionDate", LEFT).get("date");
            return query
                    .groupBy(root.get("id"), project, employee, date)
                    .orderBy(builder.asc(project), builder.asc(employee), builder.asc(date))
                    .getRestriction();
        };
    }
}
