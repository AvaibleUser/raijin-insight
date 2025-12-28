package edu.raijin.insight.dimension.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.insight.dimension.domain.model.Story;
import edu.raijin.insight.dimension.domain.port.persistence.UpdateStoryPort;
import edu.raijin.insight.dimension.domain.usecase.UpdateStoryUseCase;
import edu.raijin.insight.fact.domain.usecase.UpdateSprintStatusUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateStoryService implements UpdateStoryUseCase {

    private final UpdateStoryPort update;
    private final UpdateSprintStatusUseCase updateStatus;

    @Override
    @Transactional
    public Story update(UUID storyId, Story story) {
        Story toUpdate = update.findById(storyId)
                .orElseThrow(() -> new ValueNotFoundException("La historia no se encuentra registrada"));

        updateStatus.updatePoints(story, toUpdate);
        toUpdate.updateFrom(story);
        toUpdate.checkValidRegistration();
        Story updated = update.update(toUpdate);

        return updated;
    }
}
