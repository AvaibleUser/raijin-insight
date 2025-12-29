package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.impl;

import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.StoryActivitySpecification.byDateFrom;
import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.StoryActivitySpecification.byDateTo;
import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.StoryActivitySpecification.byDeveloper;
import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.StoryActivitySpecification.byProject;
import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.StoryActivitySpecification.orderByDeveloper;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.EmployeeProductivityReport;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.model.StoryActivity;
import edu.raijin.insight.fact.domain.port.persistence.FindStoryActivityReportPort;
import edu.raijin.insight.fact.domain.port.persistence.RegisterStoryActivityPort;
import edu.raijin.insight.fact.domain.port.persistence.UpdateStoryActivityPort;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.StoryActivityEntity;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.mapper.StoryActivityEntityMapper;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.repository.JpaStoryActivityRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class StoryActivityRepositoryAdapter
        implements RegisterStoryActivityPort, UpdateStoryActivityPort, FindStoryActivityReportPort {

    private final JpaStoryActivityRepository activityRepository;
    private final StoryActivityEntityMapper mapper;

    @Override
    public Long register(Long fromDateId, StoryActivity movement) {
        StoryActivityEntity entity = mapper.toEntity(fromDateId, movement);
        return activityRepository.save(entity).getId();
    }

    @Override
    public Optional<StoryActivity> findByStoryId(UUID storyId) {
        return activityRepository.findByStoryStoryId(storyId).map(mapper::toDomain);
    }

    @Override
    public StoryActivity update(Long fromDateId, Long toDateId, StoryActivity storyActivity) {
        StoryActivityEntity entity = mapper.toEntity(fromDateId, toDateId, storyActivity);
        return mapper.toDomain(activityRepository.save(entity));
    }

    @Override
    public Paged<EmployeeProductivityReport> findStoryActivityReport(Filter<StoryActivity> filter, Pageable pageable) {
        Specification<StoryActivityEntity> spec = byDateFrom(filter.getFrom())
                .and(byDateTo(filter.getTo()))
                .and(byProject(filter.getFilter().getProjectId()))
                .and(byDeveloper(filter.getFilter().getDeveloperId()))
                .and(orderByDeveloper());

        Page<EmployeeProductivityReport> page = activityRepository.findAll(spec, pageable).map(mapper::toReport);
        return Paged.from(page);
    }
}
