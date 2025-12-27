package edu.raijin.insight.dimension.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.insight.dimension.domain.model.User;

@UseCase
public interface UpdateUserUseCase {

    User update(UUID userId, User user);
}

