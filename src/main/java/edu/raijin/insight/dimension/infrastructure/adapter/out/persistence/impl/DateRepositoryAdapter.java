package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.model.Date;
import edu.raijin.insight.dimension.domain.port.persistence.RegisterDatePort;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.DatesEntity;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.mapper.DateEntityMapper;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.repository.JpaDateRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class DateRepositoryAdapter implements RegisterDatePort {

    private final JpaDateRepository dateRepository;
    private final DateEntityMapper mapper;

    @Override
    public Long register(Date date) {
        DatesEntity entity = mapper.toEntity(date);
        return dateRepository.save(entity).getId();
    }

    @Override
    public Optional<Date> findByDate(LocalDate date) {
        return dateRepository.findByDate(date)
                .map(mapper::toDomain);
    }
}
