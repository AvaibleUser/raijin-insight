package edu.raijin.insight.dimension.infrastructure.adapter.in.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.StoryEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.model.Story;
import edu.raijin.insight.fact.domain.model.StoryActivity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoryEventMapper {

    @Mapping(target = "storyId", source = "id")
    Story toDomain(StoryEvent event);

    @Mapping(target = "storyId", source = "id")
    StoryActivity toActivityDomain(StoryEvent event);
}
