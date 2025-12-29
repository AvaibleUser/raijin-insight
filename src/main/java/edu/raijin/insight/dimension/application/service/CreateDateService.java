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
    public Long create(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        Date date = register.findByDate(localDate).orElse(null);
        if (date != null) {
            return date.getId();
        }
        Date created = Date.from(localDate);
        return register.register(created);
    }
}
