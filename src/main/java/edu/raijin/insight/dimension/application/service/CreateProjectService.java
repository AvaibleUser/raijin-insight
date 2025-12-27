package edu.raijin.insight.dimension.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.insight.dimension.domain.model.Project;
import edu.raijin.insight.dimension.domain.port.persistence.RegisterProjectPort;
import edu.raijin.insight.dimension.domain.usecase.CreateProjectUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateProjectService implements CreateProjectUseCase {

    private final RegisterProjectPort register;

    @Override
    @Transactional
    public UUID create(Project project) {
        project.checkValidRegistration();
        return register.register(project);
    }
}

