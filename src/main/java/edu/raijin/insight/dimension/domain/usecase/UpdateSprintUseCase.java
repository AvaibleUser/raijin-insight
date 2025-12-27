package edu.raijin.insight.dimension.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.insight.dimension.domain.model.Sprint;

@UseCase
public interface UpdateSprintUseCase {

    Sprint update(UUID sprintId, Sprint sprint);
}

