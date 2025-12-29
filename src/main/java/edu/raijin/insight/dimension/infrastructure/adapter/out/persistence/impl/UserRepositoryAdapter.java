package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.impl;

import static edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.specification.UserSpecification.byHiredAtFrom;
import static edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.specification.UserSpecification.byHiredAtTo;
import static edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.specification.UserSpecification.byRole;
import static edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.specification.UserSpecification.byRoleNot;
import static edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.specification.UserSpecification.orderByRole;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.model.RoleReport;
import edu.raijin.insight.dimension.domain.model.User;
import edu.raijin.insight.dimension.domain.port.persistence.FindUserReportPort;
import edu.raijin.insight.dimension.domain.port.persistence.RegisterUserPort;
import edu.raijin.insight.dimension.domain.port.persistence.UpdateUserPort;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.UsersEntity;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.mapper.UserEntityMapper;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.repository.JpaUserRepository;
import edu.raijin.insight.fact.domain.model.Filter;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements RegisterUserPort, UpdateUserPort, FindUserReportPort {

    private final JpaUserRepository userRepository;
    private final UserEntityMapper mapper;

    @Override
    public UUID register(User user) {
        UsersEntity entity = mapper.toEntity(user);
        return userRepository.save(entity).getUserId();
    }

    @Override
    public User update(User user) {
        UsersEntity entity = mapper.toEntity(user);
        return mapper.toDomain(userRepository.save(entity));
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Paged<RoleReport> fetchRoleReport(Filter<User> filter, Pageable pageable) {
        Specification<UsersEntity> spec = byHiredAtFrom(filter.getFrom())
                .and(byHiredAtTo(filter.getTo()))
                .and(byRole(filter.getFilter().getRole()))
                .and(byRoleNot("USER"))
                .and(byRoleNot("ADMIN"))
                .and(orderByRole());

        Page<RoleReport> page = userRepository.findAll(spec, pageable).map(mapper::toReport);
        return Paged.from(page);
    }
}
