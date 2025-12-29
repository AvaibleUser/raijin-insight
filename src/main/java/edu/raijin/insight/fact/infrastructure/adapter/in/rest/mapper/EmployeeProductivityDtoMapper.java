package edu.raijin.insight.fact.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.EmployeeProductivityReport;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.model.StoryActivity;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.storyactivity.EmployeeProductivityReportDto;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.storyactivity.FilterReportDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeProductivityDtoMapper {

    @Mapping(target = "filter", source = "dto")
    Filter<StoryActivity> toDto(FilterReportDto dto);

    @Mapping(target = "projectId", source = "projectId")
    @Mapping(target = "developerId", source = "developerId")
    StoryActivity toFilter(FilterReportDto dto);

    @Mapping(target = "project.id", source = "project.projectId")
    @Mapping(target = "story.id", source = "story.storyId")
    @Mapping(target = "sprint.id", source = "sprint.sprintId")
    @Mapping(target = "productOwner.id", source = "productOwner.userId")
    @Mapping(target = "developer.id", source = "developer.userId")
    EmployeeProductivityReportDto toDto(EmployeeProductivityReport report);

    default <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }

    default LocalDate map(Instant instant) {
        return instant == null ? null : LocalDate.ofInstant(instant, ZoneId.systemDefault());
    }
}
