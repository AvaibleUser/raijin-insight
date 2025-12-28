package edu.raijin.insight.fact.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.insight.fact.domain.model.StoryActivity;

@UseCase
public interface CreateStoryActivityUseCase {

    Long create(StoryActivity storyActivity);
}
