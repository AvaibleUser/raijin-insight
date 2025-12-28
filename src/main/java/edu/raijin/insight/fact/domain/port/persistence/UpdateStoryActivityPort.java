package edu.raijin.insight.fact.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.fact.domain.model.StoryActivity;

@Port
public interface UpdateStoryActivityPort {

    Optional<StoryActivity> findByStoryId(UUID storyId);

    StoryActivity update(Long dateId, StoryActivity storyActivity);
}
