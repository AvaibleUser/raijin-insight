package edu.raijin.insight.audit.domain.usecase;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.insight.audit.domain.model.Snapshot;
import edu.raijin.insight.fact.domain.model.Filter;

@UseCase
public interface FetchSnapshotReportUseCase {

    Paged<Snapshot> fetch(Filter<Snapshot> filter, Pageable pageable);
}
