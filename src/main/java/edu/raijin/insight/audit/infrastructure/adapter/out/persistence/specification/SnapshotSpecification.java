package edu.raijin.insight.audit.infrastructure.adapter.out.persistence.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import edu.raijin.insight.audit.infrastructure.adapter.out.persistence.entity.SnapshotsEntity;

public interface SnapshotSpecification {

    static Specification<SnapshotsEntity> byOccurredAtFrom(LocalDate from) {
        if (from == null) {
            return Specification.unrestricted();
        }
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("occurredAt"), from);
    }

    static Specification<SnapshotsEntity> byOccurredAtTo(LocalDate to) {
        if (to == null) {
            return Specification.unrestricted();
        }
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("occurredAt"), to);
    }

    static Specification<SnapshotsEntity> orderByOccurredAt() {
        return (root, query, builder) -> query.orderBy(builder.desc(root.get("occurredAt"))).getRestriction();
    }
}
