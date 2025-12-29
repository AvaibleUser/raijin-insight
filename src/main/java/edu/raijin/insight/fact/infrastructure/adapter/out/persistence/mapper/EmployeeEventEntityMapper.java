package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.EmployeeEvent;
import edu.raijin.insight.fact.domain.model.EmployeeReport;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.EmployeeEventsEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeEventEntityMapper {

    @Mapping(target = "eventDate", source = "eventDate.date")
    @Mapping(target = "employeeId", source = "employee.userId")
    EmployeeEvent toDomain(EmployeeEventsEntity entity);

    @Mapping(target = "employeeId", source = "employee.userId")
    @Mapping(target = "fullName", source = "employee.fullName")
    @Mapping(target = "email", source = "employee.email")
    @Mapping(target = "hiredAt", source = "employee.hiredAt")
    @Mapping(target = "terminatedAt", source = "employee.terminatedAt")
    @Mapping(target = "eventDate", source = "eventDate.date")
    @Mapping(target = "eventType", source = "eventType")
    @Mapping(target = "role", source = "role")
    EmployeeReport toReport(EmployeeEventsEntity entity);

    @Mapping(target = "eventDate.id", source = "dateId")
    @Mapping(target = "employee.userId", source = "domain.employeeId")
    EmployeeEventsEntity toEntity(Long dateId, EmployeeEvent domain);
}
