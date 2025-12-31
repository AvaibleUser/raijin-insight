package edu.raijin.insight.audit.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.insight.audit.domain.model.Snapshot;

@UseCase
public interface CreateSnapshotUseCase {

    Long create(Snapshot snapshot);
}
