package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.model.Date;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.DatesEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DateEntityMapper {

    Date toDomain(DatesEntity entity);

    DatesEntity toEntity(Date domain);
}
