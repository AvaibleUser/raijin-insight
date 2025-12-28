package edu.raijin.insight.fact.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.fact.domain.model.StoryActivity;

@Port
public interface RegisterStoryActivityPort {

    Long register(Long fromDateId, StoryActivity storyActivity);
}
