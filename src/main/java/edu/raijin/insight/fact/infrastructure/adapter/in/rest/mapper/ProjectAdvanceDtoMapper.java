package edu.raijin.insight.fact.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.model.ProjectAdvanceReport;
import edu.raijin.insight.fact.domain.model.SprintStatus;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.sprintstatus.FilterReportDto;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.sprintstatus.ProjectAdvanceReportDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectAdvanceDtoMapper {

    @Mapping(target = "filter", source = "dto")
    Filter<SprintStatus> toDto(FilterReportDto dto);

    @Mapping(target = "projectId", source = "projectId")
    SprintStatus toFilter(FilterReportDto dto);

    ProjectAdvanceReportDto toDto(ProjectAdvanceReport report);

    default <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }
}
