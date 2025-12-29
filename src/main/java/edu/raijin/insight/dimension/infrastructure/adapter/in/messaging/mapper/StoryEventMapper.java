package edu.raijin.insight.dimension.infrastructure.adapter.in.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.time.LocalDate;

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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "storyId", source = "id")
    @Mapping(target = "toDate", source = "stage")
    StoryActivity toActivityDomain(StoryEvent event);

    default LocalDate toLocalDate(String stage) {
        return switch (stage) {
            case Story.STAGE_DONE -> LocalDate.now();
            default -> null;
        };
    }
}
