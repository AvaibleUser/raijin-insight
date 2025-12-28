package edu.raijin.insight.fact.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.fact.domain.model.SprintStatus;

@Port
public interface RegisterSprintStatusPort {

    Long register(Long fromDateId, Long toDateId, SprintStatus sprintStatus);
}
