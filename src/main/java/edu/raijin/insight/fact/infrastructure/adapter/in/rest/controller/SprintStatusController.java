package edu.raijin.insight.fact.infrastructure.adapter.in.rest.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.usecase.FetchSprintStatusReportUseCase;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.sprintstatus.FilterReportDto;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.dto.sprintstatus.ProjectAdvanceReportDto;
import edu.raijin.insight.fact.infrastructure.adapter.in.rest.mapper.ProjectAdvanceDtoMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/project/advances")
@RequiredArgsConstructor
public class SprintStatusController {

    private final FetchSprintStatusReportUseCase fetch;
    private final ProjectAdvanceDtoMapper mapper;

    @GetMapping
    public Paged<ProjectAdvanceReportDto> fetchProjectAdvance(Pageable pageable, FilterReportDto filter) {
        return fetch.fetchSprintStatusReport(mapper.toDto(filter), pageable).map(mapper::toDto);
    }
}
