package edu.raijin.insight.fact.infrastructure.adapter.in.rest.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.usecase.FetchFinancialMovementReportUseCase;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.movement.FilterReportDto;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.movement.FinancialReportDto;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.mapper.FinancialReportDtoMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/financial/movements")
@RequiredArgsConstructor
public class FinancialMovementController {

    private final FetchFinancialMovementReportUseCase fetch;
    private final FinancialReportDtoMapper mapper;

    @GetMapping
    public Paged<FinancialReportDto> fetchFinancialProjectReport(Pageable pageable, FilterReportDto filter) {
        return fetch.fetchFinancialProjectReport(mapper.toDto(filter), pageable).map(mapper::toDto);
    }
}
