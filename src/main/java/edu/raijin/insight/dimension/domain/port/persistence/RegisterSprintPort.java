package edu.raijin.insight.dimension.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.dimension.domain.model.Sprint;

@Port
public interface RegisterSprintPort {

    UUID register(Sprint sprint);
}

