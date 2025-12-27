package edu.raijin.insight.dimension.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.dimension.domain.model.Project;

@Port
public interface RegisterProjectPort {

    UUID register(Project project);
}

