package edu.raijin.insight.audit.infrastructure.adapter.in.rest.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.audit.domain.usecase.FetchSnapshotReportUseCase;
import edu.raijin.insight.audit.infrastructure.adapter.in.rest.dto.snapshot.FilterReportDto;
import edu.raijin.insight.audit.infrastructure.adapter.in.rest.mapper.SnapshotDtoMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/activity/logs")
@RequiredArgsConstructor
public class SnapshotController {

    private final FetchSnapshotReportUseCase fetch;
    private final SnapshotDtoMapper mapper;

    @GetMapping
    public Paged<SnapshotReportDto> fetch(Pageable pageable, FilterReportDto filter) {
        return fetch.fetch(mapper.toDto(filter), pageable).map(mapper::toDto);
    }
}
