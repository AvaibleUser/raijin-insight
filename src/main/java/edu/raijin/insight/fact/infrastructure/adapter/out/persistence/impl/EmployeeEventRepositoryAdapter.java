package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.impl;

import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.EmployeeEvent;
import edu.raijin.insight.fact.domain.port.persistence.RegisterEmployeeEventPort;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.EmployeeEventsEntity;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.mapper.EmployeeEventEntityMapper;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.repository.JpaEmployeeEventRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class EmployeeEventRepositoryAdapter implements RegisterEmployeeEventPort {

    private final JpaEmployeeEventRepository eventRepository;
    private final EmployeeEventEntityMapper mapper;

    @Override
    public Long register(Long eventId, EmployeeEvent employee) {
        EmployeeEventsEntity entity = mapper.toEntity(eventId, employee);
        return eventRepository.save(entity).getId();
    }
}
