package edu.raijin.insight.fact.application.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.insight.dimension.domain.usecase.CreateDateUseCase;
import edu.raijin.insight.fact.domain.model.StoryActivity;
import edu.raijin.insight.fact.domain.port.persistence.RegisterStoryActivityPort;
import edu.raijin.insight.fact.domain.usecase.CreateStoryActivityUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateStoryActivityService implements CreateStoryActivityUseCase {

    private final RegisterStoryActivityPort register;
    private final CreateDateUseCase createDate;

    @Override
    @Transactional
    public Long create(StoryActivity storyActivity) {
        Long dateId = createDate.create(LocalDate.now());
        storyActivity.checkValidRegistration();
        return register.register(dateId, storyActivity);
    }
}
