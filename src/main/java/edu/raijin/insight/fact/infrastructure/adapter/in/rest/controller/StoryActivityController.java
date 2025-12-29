package edu.raijin.insight.fact.infrastructure.adapter.in.rest.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.usecase.FetchStoryActivityReportUseCase;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.storyactivity.EmployeeProductivityReportDto;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.storyactivity.FilterReportDto;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.mapper.EmployeeProductivityDtoMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/employees/productivity")
@RequiredArgsConstructor
public class StoryActivityController {

    private final FetchStoryActivityReportUseCase fetch;
    private final EmployeeProductivityDtoMapper mapper;

    @GetMapping
    public Paged<EmployeeProductivityReportDto> fetchEmployeeProductivity(Pageable pageable, FilterReportDto filter) {
        return fetch.fetchStoryActivityReport(mapper.toDto(filter), pageable).map(mapper::toDto);
    }
}
