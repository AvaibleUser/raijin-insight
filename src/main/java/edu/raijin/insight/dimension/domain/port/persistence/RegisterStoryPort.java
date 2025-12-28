package edu.raijin.insight.dimension.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.dimension.domain.model.Story;

@Port
public interface RegisterStoryPort {

    Story register(Story story);
}
