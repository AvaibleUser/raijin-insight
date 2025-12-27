package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.EmployeeEvent;
import edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity.EmployeeEventsEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeEventEntityMapper {

    @Mapping(target = "eventDate", source = "eventDate.date")
    @Mapping(target = "employeeId", source = "employee.userId")
    EmployeeEvent toDomain(EmployeeEventsEntity entity);

    @Mapping(target = "eventDate.id", source = "dateId")
    @Mapping(target = "employee.userId", source = "domain.employeeId")
    EmployeeEventsEntity toEntity(Long dateId, EmployeeEvent domain);
}
