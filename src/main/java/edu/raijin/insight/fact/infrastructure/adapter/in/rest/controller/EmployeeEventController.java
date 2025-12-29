package edu.raijin.insight.fact.infrastructure.adapter.in.rest.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.usecase.FetchEmployeeEventReportUseCase;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.employeevent.EmployeeReportDto;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.employeevent.FilterReportDto;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.mapper.EmployeeEventDtoMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/employees/events")
@RequiredArgsConstructor
public class EmployeeEventController {

    private final FetchEmployeeEventReportUseCase fetch;
    private final EmployeeEventDtoMapper mapper;

    @GetMapping
    public Paged<EmployeeReportDto> fetchEmployeeReport(Pageable pageable, FilterReportDto filter) {
        return fetch.fetchEventsReport(mapper.toDto(filter), pageable).map(mapper::toDto);
    }
}
