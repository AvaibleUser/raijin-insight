package edu.raijin.insight.audit.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.audit.domain.model.Snapshot;

@Port
public interface RegisterSnapshotPort {

    Long register(Snapshot snapshot);
}
