package edu.raijin.insight.fact.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.math.BigDecimal;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.Filter;
import edu.raijin.insight.fact.domain.model.FinancialMovement;
import edu.raijin.insight.fact.domain.model.FinancialReport;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.movement.FilterReportDto;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.movement.FinancialReportDto;;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FinancialReportDtoMapper {

    @Mapping(target = "filter", source = "dto")
    Filter<FinancialMovement> toDto(FilterReportDto dto);

    @Mapping(target = "projectId", source = "projectId")
    @Mapping(target = "employeeId", source = "employeeId")
    @Mapping(target = "amount", source = "incomes")
    FinancialMovement toFilter(FilterReportDto dto);

    FinancialReportDto toDto(FinancialReport report);

    default <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }

    default BigDecimal map(Boolean value) {
        return value == null ? null : value ? BigDecimal.ONE : BigDecimal.ONE.negate();
    }
}
