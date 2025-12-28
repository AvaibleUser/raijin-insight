package edu.raijin.insight.fact.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.insight.fact.domain.model.SprintStatus;

@UseCase
public interface CreateSprintStatusUseCase {

    Long create(SprintStatus sprintStatus);
}
