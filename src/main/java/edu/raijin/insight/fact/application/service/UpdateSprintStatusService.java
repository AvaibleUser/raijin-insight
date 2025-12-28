package edu.raijin.insight.fact.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.insight.dimension.domain.model.Story;
import edu.raijin.insight.dimension.domain.usecase.CreateDateUseCase;
import edu.raijin.insight.fact.domain.model.SprintStatus;
import edu.raijin.insight.fact.domain.port.persistence.UpdateSprintStatusPort;
import edu.raijin.insight.fact.domain.usecase.UpdateSprintStatusUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateSprintStatusService implements UpdateSprintStatusUseCase {

    private final UpdateSprintStatusPort update;
    private final CreateDateUseCase createDate;

    private SprintStatus save(SprintStatus sprintStatus) {
        sprintStatus.checkValidRegistration();
        Long fromDateId = createDate.create(sprintStatus.getFromDate());
        Long toDateId = createDate.create(sprintStatus.getToDate());

        return update.update(fromDateId, toDateId, sprintStatus);
    }

    @Override
    @Transactional
    public SprintStatus update(SprintStatus sprintStatus) {
        SprintStatus updated = update.findBySprintId(sprintStatus.getSprintId())
                .orElseThrow(() -> new ValueNotFoundException("El estado del sprint no se encuentra registrado"));

        updated.updateFrom(sprintStatus);
        return save(updated);
    }

    @Override
    @Transactional
    public void updatePoints(Story newStory, Story oldStory) {
        Optional.ofNullable(newStory)
                .map(Story::getSprintId)
                .flatMap(update::findBySprintId)
                .ifPresent(sprintStatus -> {
                    sprintStatus.updateFrom(newStory, oldStory);
                    save(sprintStatus);
                });

        Optional.ofNullable(oldStory)
                .map(Story::getSprintId)
                .flatMap(update::findBySprintId)
                .ifPresent(sprintStatus -> {
                    sprintStatus.updateFrom(newStory, oldStory);
                    save(sprintStatus);
                });
    }
}
