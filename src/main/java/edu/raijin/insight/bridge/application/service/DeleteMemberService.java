package edu.raijin.insight.bridge.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.insight.bridge.domain.port.persistence.DeleteMemberPort;
import edu.raijin.insight.bridge.domain.usecase.DeleteMemberUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteMemberService implements DeleteMemberUseCase {

    private final DeleteMemberPort delete;

    @Override
    @Transactional
    public void delete(UUID projectId, UUID userId) {
        if (!delete.existsMember(projectId, userId)) {
            return;
        }

        delete.delete(projectId, userId);
    }
}
