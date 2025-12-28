package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.FinancialMovement;
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
}
