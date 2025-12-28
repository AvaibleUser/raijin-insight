package edu.raijin.insight.dimension.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.insight.dimension.domain.model.Story;
import edu.raijin.insight.dimension.domain.port.persistence.RegisterStoryPort;
import edu.raijin.insight.dimension.domain.usecase.CreateStoryUseCase;
import edu.raijin.insight.fact.domain.usecase.UpdateSprintStatusUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateStoryService implements CreateStoryUseCase {

    private final RegisterStoryPort register;
    private final UpdateSprintStatusUseCase updateStatus;

    @Override
    @Transactional
    public UUID create(Story story) {
        story.checkValidRegistration();
        Story newStory = register.register(story);

        updateStatus.updatePoints(newStory, null);

        return newStory.getStoryId();
    }
}
