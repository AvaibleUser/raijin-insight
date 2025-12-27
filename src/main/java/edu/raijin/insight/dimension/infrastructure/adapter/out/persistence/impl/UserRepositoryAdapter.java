package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.model.User;
import edu.raijin.insight.dimension.domain.port.persistence.RegisterUserPort;
import edu.raijin.insight.dimension.domain.port.persistence.UpdateUserPort;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.UsersEntity;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.mapper.UserEntityMapper;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements RegisterUserPort, UpdateUserPort {

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
}

