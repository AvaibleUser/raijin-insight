package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.DatesEntity;
import edu.raijin.insight.fact.domain.model.FinancialMovement;
import edu.raijin.insight.fact.domain.model.FinancialReport;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.FinancialMovementsEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FinancialMovementEntityMapper {

    @Mapping(target = "transactionDate", source = "transactionDate.date")
    @Mapping(target = "projectId", source = "project.projectId")
    @Mapping(target = "employeeId", source = "employee.userId")
    FinancialMovement toDomain(FinancialMovementsEntity entity);

    @Mapping(target = "transactionDate.id", source = "dateId")
    @Mapping(target = "project.projectId", source = "domain.projectId")
    @Mapping(target = "employee.userId", source = "domain.employeeId")
    FinancialMovementsEntity toEntity(Long dateId, FinancialMovement domain);

    @Mapping(target = "projectId", source = "project.projectId")
    @Mapping(target = "projectName", source = "project.name")
    @Mapping(target = "client", source = "project.client")
    @Mapping(target = "projectStartDate", source = "project.startDate")
    @Mapping(target = "projectEndDate", source = "project.endDate")
    @Mapping(target = "employeeId", source = "employee.userId")
    @Mapping(target = "fullName", source = "employee.fullName")
    @Mapping(target = "email", source = "employee.email")
    @Mapping(target = "role", source = "employee.role")
    @Mapping(target = "hiredAt", source = "employee.hiredAt")
    @Mapping(target = "terminatedAt", source = "employee.terminatedAt")
    FinancialReport toReport(FinancialMovementsEntity entity);

    default LocalDate map(DatesEntity entity) {
        return entity == null ? null : entity.getDate();
    }

    default LocalDate map(Instant instant) {
        return instant == null ? null : LocalDate.ofInstant(instant, ZoneId.systemDefault());
    }
}
