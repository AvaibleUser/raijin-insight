package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.model.Sprint;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.SprintsEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SprintEntityMapper {

    Sprint toDomain(SprintsEntity entity);

    SprintsEntity toEntity(Sprint domain);
}

