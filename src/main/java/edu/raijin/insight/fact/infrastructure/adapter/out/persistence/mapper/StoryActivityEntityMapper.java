package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.StoryActivity;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.StoryActivityEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoryActivityEntityMapper {

    StoryActivity toDomain(StoryActivityEntity entity);

    @Mapping(target = "date.id", source = "dateId")
    StoryActivityEntity toEntity(Long dateId, StoryActivity domain);
}
