package edu.raijin.insight.dimension.infrastructure.adapter.in.rest.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.usecase.FetchUserReportUseCase;
import edu.raijin.insight.dimension.infrastructure.adapter.in.rest.dto.user.FilterReportDto;
import edu.raijin.insight.dimension.infrastructure.adapter.in.rest.dto.user.RoleReportDto;
import edu.raijin.insight.dimension.infrastructure.adapter.in.rest.mapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/users/roles")
@RequiredArgsConstructor
public class UserController {

    private final FetchUserReportUseCase fetch;
    private final UserDtoMapper mapper;

    @GetMapping
    public Paged<RoleReportDto> fetchRoleReport(Pageable pageable, FilterReportDto filter) {
        return fetch.fetchRoleReport(mapper.toDto(filter), pageable).map(mapper::toDto);
    }
}
