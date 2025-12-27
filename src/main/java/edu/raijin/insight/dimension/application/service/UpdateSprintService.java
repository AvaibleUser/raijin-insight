package edu.raijin.insight.dimension.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.insight.dimension.domain.model.Sprint;
import edu.raijin.insight.dimension.domain.port.persistence.UpdateSprintPort;
import edu.raijin.insight.dimension.domain.usecase.UpdateSprintUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateSprintService implements UpdateSprintUseCase {

    private final UpdateSprintPort update;

    @Override
    @Transactional
    public Sprint update(UUID sprintId, Sprint sprint) {
        Sprint toUpdate = update.findById(sprintId)
                .orElseThrow(() -> new ValueNotFoundException("El empleado no se encuentra registrado"));

        toUpdate.updateFrom(sprint);
        toUpdate.checkValidRegistration();
        return update.update(toUpdate);
    }
}

