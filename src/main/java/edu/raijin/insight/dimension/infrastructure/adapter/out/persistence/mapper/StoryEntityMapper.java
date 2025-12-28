package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.model.Story;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.StoriesEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoryEntityMapper {

    @Mapping(target = "sprintId", source = "sprint.sprintId")
    Story toDomain(StoriesEntity entity);

    @Mapping(target = "sprint.sprintId", source = "sprintId")
    StoriesEntity toEntity(Story domain);
}
