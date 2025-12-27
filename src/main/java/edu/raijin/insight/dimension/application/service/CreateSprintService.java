package edu.raijin.insight.dimension.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.insight.dimension.domain.model.Sprint;
import edu.raijin.insight.dimension.domain.port.persistence.RegisterSprintPort;
import edu.raijin.insight.dimension.domain.usecase.CreateSprintUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateSprintService implements CreateSprintUseCase {

    private final RegisterSprintPort register;

    @Override
    @Transactional
    public UUID create(Sprint sprint) {
        sprint.checkValidRegistration();
        return register.register(sprint);
    }
}

