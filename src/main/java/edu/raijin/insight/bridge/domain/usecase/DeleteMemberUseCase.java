package edu.raijin.insight.bridge.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeleteMemberUseCase {

    void delete(UUID projectId, UUID userId);
}
