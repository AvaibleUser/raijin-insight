package edu.raijin.insight.fact.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.insight.dimension.domain.usecase.CreateDateUseCase;
import edu.raijin.insight.fact.domain.model.StoryActivity;
import edu.raijin.insight.fact.domain.port.persistence.UpdateStoryActivityPort;
import edu.raijin.insight.fact.domain.usecase.UpdateStoryActivityUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateStoryActivityService implements UpdateStoryActivityUseCase {

    private final UpdateStoryActivityPort update;
    private final CreateDateUseCase createDate;

    @Override
    @Transactional
    public StoryActivity update(StoryActivity storyActivity) {
        StoryActivity toUpdate = update.findByStoryId(storyActivity.getStoryId()).orElseThrow(
                () -> new ValueNotFoundException("La actividad de la historia no se encuentra registrada"));

        toUpdate.updateFrom(storyActivity);
        toUpdate.checkValidRegistration();
        Long fromDateId = createDate.create(toUpdate.getFromDate());
        Long toDateId = createDate.create(storyActivity.getToDate());

        return update.update(fromDateId, toDateId, toUpdate);
    }
}
