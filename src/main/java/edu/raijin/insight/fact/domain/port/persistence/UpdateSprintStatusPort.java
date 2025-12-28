package edu.raijin.insight.fact.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.fact.domain.model.SprintStatus;

@Port
public interface UpdateSprintStatusPort {

    Optional<SprintStatus> findBySprintId(UUID sprintId);

    SprintStatus update(Long fromDateId, Long toDateId, SprintStatus sprintStatus);
}
