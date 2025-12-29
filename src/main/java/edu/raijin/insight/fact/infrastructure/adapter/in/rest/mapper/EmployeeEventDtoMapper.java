package edu.raijin.insight.fact.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.EmployeeEvent;
import edu.raijin.insight.fact.domain.model.EmployeeReport;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.employeevent.EmployeeReportDto;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.employeevent.FilterReportDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeEventDtoMapper {

    @Mapping(target = "filter", source = "dto")
    Filter<EmployeeEvent> toDto(FilterReportDto dto);

    @Mapping(target = "eventType", source = "eventType")
    @Mapping(target = "employeeId", source = "employeeId")
    EmployeeEvent toFilter(FilterReportDto dto);

    EmployeeReportDto toDto(EmployeeReport report);

    default <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }
}
