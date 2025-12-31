package edu.raijin.insight.audit.application.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.insight.audit.domain.model.Snapshot;
import edu.raijin.insight.audit.domain.port.persistence.FindSnapshotReportPort;
import edu.raijin.insight.audit.domain.usecase.FetchSnapshotReportUseCase;
import edu.raijin.insight.fact.domain.model.Filter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchSnapshotReportService implements FetchSnapshotReportUseCase {

    private final FindSnapshotReportPort find;

    @Override
    @Transactional
    public Paged<Snapshot> fetch(Filter<Snapshot> filter, Pageable pageable) {
        return find.find(filter, pageable);
    }
}
