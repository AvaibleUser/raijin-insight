package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.UsersEntity;

public interface UserSpecification {

    static Specification<UsersEntity> byHiredAtFrom(LocalDate from) {
        if (from == null) {
            return Specification.unrestricted();
        }
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("hiredAt"), from);
    }

    static Specification<UsersEntity> byHiredAtTo(LocalDate to) {
        if (to == null) {
            return Specification.unrestricted();
        }
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("hiredAt"), to);
    }

    static Specification<UsersEntity> byRole(String role) {
        if (role == null) {
            return Specification.unrestricted();
        }
        return (root, query, builder) -> builder.equal(root.get("role"), role);
    }

    static Specification<UsersEntity> byRoleNot(String role) {
        if (role == null) {
            return Specification.unrestricted();
        }
        return (root, query, builder) -> builder.notEqual(root.get("role"), role);
    }

    static Specification<UsersEntity> orderByRole() {
        return (root, query, builder) -> query.orderBy(builder.asc(root.get("role"))).getRestriction();
    }
}
