package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.DatesEntity;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.UsersEntity;
import edu.raijin.insight.fact.domain.model.ProjectAdvanceReport;
import edu.raijin.insight.fact.domain.model.SprintStatus;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.SprintStatusEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SprintStatusEntityMapper {

    @Mapping(target = "projectId", source = "project.projectId")
    @Mapping(target = "sprintId", source = "sprint.sprintId")
    @Mapping(target = "fromDate", source = "fromDate.date")
    @Mapping(target = "toDate", source = "toDate.date")
    @Mapping(target = "pointsDone", source = "pointsDone", defaultValue = "0")
    @Mapping(target = "pointsPlanned", source = "pointsPlanned", defaultValue = "0")
    @Mapping(target = "percentDone", source = "percentDone", defaultValue = "0")
    SprintStatus toDomain(SprintStatusEntity entity);

    @Mapping(target = "project.projectId", source = "domain.projectId")
    @Mapping(target = "sprint.sprintId", source = "domain.sprintId")
    @Mapping(target = "fromDate", ignore = true)
    @Mapping(target = "toDate", ignore = true)
    @Mapping(target = "fromDate.id", source = "fromDateId")
    @Mapping(target = "toDate.id", source = "toDateId")
    SprintStatusEntity toEntity(Long fromDateId, Long toDateId, SprintStatus domain);

    @Mapping(target = "projectId", source = "project.projectId")
    @Mapping(target = "projectName", source = "project.name")
    @Mapping(target = "client", source = "project.client")
    @Mapping(target = "projectStartDate", source = "project.startDate")
    @Mapping(target = "projectEndDate", source = "project.endDate")
    @Mapping(target = "sprintId", source = "sprint.sprintId")
    @Mapping(target = "sprintName", source = "sprint.name")
    @Mapping(target = "sprintStatus", source = "sprint.status")
    @Mapping(target = "sprintStartDate", source = "sprint.startDate")
    @Mapping(target = "sprintEndDate", source = "sprint.endDate")
    @Mapping(target = "pointsDone", source = "pointsDone")
    @Mapping(target = "pointsPlanned", source = "pointsPlanned")
    @Mapping(target = "percentDone", source = "percentDone")
    @Mapping(target = "members", source = "project.members")
    ProjectAdvanceReport toReport(SprintStatusEntity entity);

    @Mapping(target = "id", source = "userId")
    List<UsersEntity> toEntity(List<ProjectAdvanceReport.ProjectMember> members);

    default LocalDate map(DatesEntity entity) {
        return entity == null ? null : entity.getDate();
    }

    default LocalDate map(Instant instant) {
        return instant == null ? null : LocalDate.ofInstant(instant, ZoneId.systemDefault());
    }
}
