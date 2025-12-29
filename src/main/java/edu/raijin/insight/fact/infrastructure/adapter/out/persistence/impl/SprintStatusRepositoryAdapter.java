package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.impl;

import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.SprintStatusSpecification.byDateFrom;
import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.SprintStatusSpecification.byDateTo;
import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.SprintStatusSpecification.byProject;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.model.ProjectAdvanceReport;
import edu.raijin.insight.fact.domain.model.SprintStatus;
import edu.raijin.insight.fact.domain.port.persistence.FindSprintStatusReportPort;
import edu.raijin.insight.fact.domain.port.persistence.RegisterSprintStatusPort;
import edu.raijin.insight.fact.domain.port.persistence.UpdateSprintStatusPort;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.SprintStatusEntity;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.mapper.SprintStatusEntityMapper;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.repository.JpaSprintStatusRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class SprintStatusRepositoryAdapter
        implements RegisterSprintStatusPort, UpdateSprintStatusPort, FindSprintStatusReportPort {

    private final JpaSprintStatusRepository sprintRepository;
    private final SprintStatusEntityMapper mapper;

    @Override
    public Long register(Long fromDateId, Long toDateId, SprintStatus movement) {
        SprintStatusEntity entity = mapper.toEntity(fromDateId, toDateId, movement);
        return sprintRepository.save(entity).getId();
    }

    @Override
    public Optional<SprintStatus> findBySprintId(UUID sprintId) {
        return sprintRepository.findBySprintSprintId(sprintId).map(mapper::toDomain);
    }

    @Override
    public SprintStatus update(Long fromDateId, Long toDateId, SprintStatus sprintStatus) {
        SprintStatusEntity entity = mapper.toEntity(fromDateId, toDateId, sprintStatus);
        return mapper.toDomain(sprintRepository.save(entity));
    }

    @Override
    public Paged<ProjectAdvanceReport> findSprintStatusReport(Filter<SprintStatus> filter, Pageable pageable) {
        Specification<SprintStatusEntity> spec = byDateFrom(filter.getFrom())
                .and(byDateTo(filter.getTo()))
                .and(byProject(filter.getFilter().getProjectId()));

        Page<ProjectAdvanceReport> page = sprintRepository.findAll(spec, pageable).map(mapper::toReport);
        return Paged.from(page);
    }
}
