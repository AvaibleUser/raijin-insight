package edu.raijin.insight.dimension.application.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.insight.dimension.domain.model.Date;
import edu.raijin.insight.dimension.domain.port.persistence.RegisterDatePort;
import edu.raijin.insight.dimension.domain.usecase.CreateDateUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateDateService implements CreateDateUseCase {

    private final RegisterDatePort register;

    @Override
    @Transactional
    public Long create(LocalDate date) {
        Date createdDate = new Date().withDate(date);
        createdDate.fillData();
        return register.register(createdDate);
    }
}
