package edu.raijin.insight.dimension.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.model.RoleReport;
import edu.raijin.insight.dimension.domain.model.User;
import edu.raijin.insight.dimension.infrastructure.adapter.in.rest.dto.user.FilterReportDto;
import edu.raijin.insight.dimension.infrastructure.adapter.in.rest.dto.user.RoleReportDto;
import edu.raijin.insight.fact.domain.model.Filter;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {

    @Mapping(target = "filter", source = "dto")
    Filter<User> toDto(FilterReportDto dto);

    @Mapping(target = "role", source = "role")
    User toFilter(FilterReportDto dto);

    RoleReportDto toDto(RoleReport report);

    default <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }
}
