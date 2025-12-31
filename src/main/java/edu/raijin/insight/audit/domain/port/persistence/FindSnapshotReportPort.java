package edu.raijin.insight.audit.domain.port.persistence;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.audit.domain.model.Snapshot;
import edu.raijin.insight.fact.domain.model.Filter;

@Port
public interface FindSnapshotReportPort {

    Paged<Snapshot> find(Filter<Snapshot> filter, Pageable pageable);
}
