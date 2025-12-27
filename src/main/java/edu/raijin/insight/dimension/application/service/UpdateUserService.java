package edu.raijin.insight.dimension.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.insight.dimension.domain.model.User;
import edu.raijin.insight.dimension.domain.port.persistence.UpdateUserPort;
import edu.raijin.insight.dimension.domain.usecase.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {

    private final UpdateUserPort update;

    @Override
    @Transactional
    public User update(UUID userId, User user) {
        User toUpdate = update.findById(userId)
                .orElseThrow(() -> new ValueNotFoundException("El empleado no se encuentra registrado"));

        toUpdate.updateFrom(user);
        toUpdate.checkValidRegistration();
        return update.update(toUpdate);
    }
}

