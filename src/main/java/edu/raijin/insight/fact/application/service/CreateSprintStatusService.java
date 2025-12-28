package edu.raijin.insight.fact.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.insight.dimension.domain.usecase.CreateDateUseCase;
import edu.raijin.insight.fact.domain.model.SprintStatus;
import edu.raijin.insight.fact.domain.port.persistence.RegisterSprintStatusPort;
import edu.raijin.insight.fact.domain.usecase.CreateSprintStatusUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateSprintStatusService implements CreateSprintStatusUseCase {

    private final RegisterSprintStatusPort register;
    private final CreateDateUseCase createDate;

    @Override
    @Transactional
    public Long create(SprintStatus sprintStatus) {
        Long fromDateId = createDate.create(sprintStatus.getFromDate());
        Long toDateId = createDate.create(sprintStatus.getToDate());

        sprintStatus.checkValidRegistration();
        return register.register(fromDateId, toDateId, sprintStatus);
    }
}
