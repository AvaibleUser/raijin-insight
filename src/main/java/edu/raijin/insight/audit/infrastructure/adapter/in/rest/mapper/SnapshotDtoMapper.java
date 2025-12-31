package edu.raijin.insight.audit.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.audit.domain.model.Snapshot;
import edu.raijin.insight.audit.infrastructure.adapter.in.rest.controller.SnapshotReportDto;
import edu.raijin.insight.audit.infrastructure.adapter.in.rest.dto.snapshot.FilterReportDto;
import edu.raijin.insight.fact.domain.model.Filter;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SnapshotDtoMapper {

    @Mapping(target = "filter", source = "dto")
    Filter<Snapshot> toDto(FilterReportDto dto);

    Snapshot toFilter(FilterReportDto dto);

    SnapshotReportDto toDto(Snapshot report);

    default <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }
}
