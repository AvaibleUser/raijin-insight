package edu.raijin.insight.dimension.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.insight.dimension.domain.model.Story;

@UseCase
public interface UpdateStoryUseCase {

    Story update(UUID storyId, Story story);
}

