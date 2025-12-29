package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.impl;

import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.EmployeeEventSpecification.byEmployee;
import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.EmployeeEventSpecification.byEventDateFrom;
import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.EmployeeEventSpecification.byEventDateTo;
import static edu.raijin.insight.fact.infrastructure.adapter.out.persistence.specification.EmployeeEventSpecification.byEventType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.EmployeeEvent;
import edu.raijin.insight.fact.domain.model.EmployeeReport;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.port.persistence.FindEmployeeEventReportPort;
import edu.raijin.insight.fact.domain.port.persistence.RegisterEmployeeEventPort;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.EmployeeEventsEntity;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.mapper.EmployeeEventEntityMapper;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.repository.JpaEmployeeEventRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class EmployeeEventRepositoryAdapter implements RegisterEmployeeEventPort, FindEmployeeEventReportPort {

    private final JpaEmployeeEventRepository eventRepository;
    private final EmployeeEventEntityMapper mapper;

    @Override
    public Long register(Long eventId, EmployeeEvent employee) {
        EmployeeEventsEntity entity = mapper.toEntity(eventId, employee);
        return eventRepository.save(entity).getId();
    }

    @Override
    public Paged<EmployeeReport> findEventsReport(Filter<EmployeeEvent> filter, Pageable pageable) {
        Specification<EmployeeEventsEntity> spec = byEventDateFrom(filter.getFrom())
                .and(byEventDateTo(filter.getTo()))
                .and(byEventType(filter.getFilter().getEventType()))
                .and(byEmployee(filter.getFilter().getEmployeeId()));

        Page<EmployeeReport> page = eventRepository.findAll(spec, pageable).map(mapper::toReport);
        return Paged.from(page);
    }
}
