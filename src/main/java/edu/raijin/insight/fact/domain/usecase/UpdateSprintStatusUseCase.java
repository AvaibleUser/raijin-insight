package edu.raijin.insight.fact.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.insight.dimension.domain.model.Story;
import edu.raijin.insight.fact.domain.model.SprintStatus;

@UseCase
public interface UpdateSprintStatusUseCase {

    SprintStatus update(SprintStatus sprintStatus);

    void updatePoints(Story newStory, Story oldStory);
}
