package edu.raijin.insight.dimension.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.dimension.domain.model.Sprint;

@Port
public interface UpdateSprintPort {

    Optional<Sprint> findById(UUID id);

    Sprint update(Sprint sprint);
}

