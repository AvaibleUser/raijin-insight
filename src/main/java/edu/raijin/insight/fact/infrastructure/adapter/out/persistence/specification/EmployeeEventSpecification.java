package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.domain.Specification;

import edu.raijin.commons.domain.type.EmployeeEventType;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.EmployeeEventsEntity;
import jakarta.persistence.criteria.Path;

public interface EmployeeEventSpecification {

    static Specification<EmployeeEventsEntity> byEventDateFrom(LocalDate from) {
        if (from == null) {
            return Specification.unrestricted();
        }
        return (root, query, builder) -> {
            Path<LocalDate> date = root.join("eventDate").get("date");
            return query
                    .where(builder.greaterThanOrEqualTo(date, from))
                    .groupBy(root.get("id"), date)
                    .getRestriction();
        };
    }

    static Specification<EmployeeEventsEntity> byEventDateTo(LocalDate to) {
        if (to == null) {
            return Specification.unrestricted();
        }
        return (root, query, builder) -> {
            Path<LocalDate> date = root.join("eventDate").get("date");
            return query
                    .where(builder.lessThanOrEqualTo(date, to))
                    .groupBy(root.get("id"), date)
                    .getRestriction();
        };
    }

    static Specification<EmployeeEventsEntity> groupByEmployee() {
        return (root, query, builder) -> query
                .groupBy(root.join("employee").get("userId"))
                .getRestriction();
    }

    static PredicateSpecification<EmployeeEventsEntity> byEventType(EmployeeEventType eventType) {
        if (eventType == null) {
            return PredicateSpecification.unrestricted();
        }
        return (from, builder) -> builder.equal(from.get("eventType"), eventType);
    }

    static PredicateSpecification<EmployeeEventsEntity> byEmployee(UUID employeeId) {
        if (employeeId == null) {
            return PredicateSpecification.unrestricted();
        }
        return (from, builder) -> builder.equal(from.get("employee").get("userId"), employeeId);
    }
}
