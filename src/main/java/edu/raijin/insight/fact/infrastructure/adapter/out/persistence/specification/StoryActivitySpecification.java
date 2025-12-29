package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.domain.Specification;

import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.StoryActivityEntity;
import jakarta.persistence.criteria.Path;

public interface StoryActivitySpecification {

    static Specification<StoryActivityEntity> byDateFrom(LocalDate from) {
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

    static Specification<StoryActivityEntity> byDateTo(LocalDate to) {
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

    static PredicateSpecification<StoryActivityEntity> byProject(UUID projectId) {
        if (projectId == null) {
            return PredicateSpecification.unrestricted();
        }
        return (from, builder) -> builder.equal(from.get("project").get("projectId"), projectId);
    }

    static PredicateSpecification<StoryActivityEntity> byProductOwner(UUID productOwnerId) {
        if (productOwnerId == null) {
            return PredicateSpecification.unrestricted();
        }
        return (from, builder) -> builder.equal(from.get("productOwner").get("userId"), productOwnerId);
    }

    static PredicateSpecification<StoryActivityEntity> byDeveloper(UUID developerId) {
        if (developerId == null) {
            return PredicateSpecification.unrestricted();
        }
        return (from, builder) -> builder.equal(from.get("developer").get("userId"), developerId);
    }

    static Specification<StoryActivityEntity> orderByDeveloper() {
        return (root, query, builder) -> query
                .orderBy(builder.asc(root.get("developer").get("userId")))
                .getRestriction();
    }
}
