package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.domain.Specification;

import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.SprintStatusEntity;
import jakarta.persistence.criteria.Path;

public interface SprintStatusSpecification {

    static Specification<SprintStatusEntity> byDateFrom(LocalDate from) {
        if (from == null) {
            return Specification.unrestricted();
        }
        return (root, query, builder) -> {
            Path<LocalDate> fromDate = root.join("fromDate").get("date");
            Path<LocalDate> toDate = root.join("toDate").get("date");
            return query
                    .where(builder.greaterThanOrEqualTo(fromDate, from), builder.greaterThanOrEqualTo(toDate, from))
                    .groupBy(root.get("id"), fromDate, toDate)
                    .getRestriction();
        };
    }

    static Specification<SprintStatusEntity> byDateTo(LocalDate to) {
        if (to == null) {
            return Specification.unrestricted();
        }
        return (root, query, builder) -> {
            Path<LocalDate> fromDate = root.join("fromDate").get("date");
            Path<LocalDate> toDate = root.join("toDate").get("date");
            return query
                    .where(builder.lessThanOrEqualTo(fromDate, to), builder.lessThanOrEqualTo(toDate, to))
                    .groupBy(root.get("id"), fromDate, toDate)
                    .getRestriction();
        };
    }

    static PredicateSpecification<SprintStatusEntity> byProject(UUID projectId) {
        if (projectId == null) {
            return PredicateSpecification.unrestricted();
        }
        return (from, builder) -> builder.equal(from.get("project").get("projectId"), projectId);
    }
}
