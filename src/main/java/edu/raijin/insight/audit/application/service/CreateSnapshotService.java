package edu.raijin.insight.audit.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.insight.audit.domain.model.Snapshot;
import edu.raijin.insight.audit.domain.port.persistence.RegisterSnapshotPort;
import edu.raijin.insight.audit.domain.usecase.CreateSnapshotUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateSnapshotService implements CreateSnapshotUseCase {

    private final RegisterSnapshotPort register;

    @Override
    @Transactional
    public Long create(Snapshot snapshot) {
        snapshot.checkValidRegistration();
        return register.register(snapshot);
    }
}
