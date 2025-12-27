package edu.raijin.insight.dimension.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.insight.dimension.domain.model.Project;
import edu.raijin.insight.dimension.domain.port.persistence.UpdateProjectPort;
import edu.raijin.insight.dimension.domain.usecase.UpdateProjectUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateProjectService implements UpdateProjectUseCase {

    private final UpdateProjectPort update;

    @Override
    @Transactional
    public Project update(UUID projectId, Project project) {
        Project toUpdate = update.findById(projectId)
                .orElseThrow(() -> new ValueNotFoundException("El empleado no se encuentra registrado"));

        toUpdate.updateFrom(project);
        toUpdate.checkValidRegistration();
        return update.update(toUpdate);
    }
}

