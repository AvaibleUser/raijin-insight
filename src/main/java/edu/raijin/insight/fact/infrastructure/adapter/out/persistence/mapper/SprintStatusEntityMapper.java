package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.DatesEntity;
import edu.raijin.insight.fact.domain.model.SprintStatus;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.SprintStatusEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SprintStatusEntityMapper {

    SprintStatus toDomain(SprintStatusEntity entity);

    @Mapping(target = "fromDate", ignore = true)
    @Mapping(target = "toDate", ignore = true)
    @Mapping(target = "fromDate.id", source = "fromDateId")
    @Mapping(target = "toDate.id", source = "toDateId")
    SprintStatusEntity toEntity(Long fromDateId, Long toDateId, SprintStatus domain);

    default LocalDate map(DatesEntity entity) {
        return entity == null ? null : entity.getDate();
    }
}
