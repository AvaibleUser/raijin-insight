package edu.raijin.insight.dimension.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.insight.dimension.domain.model.User;
import edu.raijin.insight.dimension.domain.port.persistence.RegisterUserPort;
import edu.raijin.insight.dimension.domain.usecase.CreateUserUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {

    private final RegisterUserPort register;

    @Override
    @Transactional
    public UUID create(User user) {
        user.checkValidRegistration();
        return register.register(user);
    }
}

