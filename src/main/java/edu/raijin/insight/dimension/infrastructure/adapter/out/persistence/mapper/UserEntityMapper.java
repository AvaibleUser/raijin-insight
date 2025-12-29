package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.model.RoleReport;
import edu.raijin.insight.dimension.domain.model.User;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.UsersEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    User toDomain(UsersEntity entity);

    @Mapping(target = "employeeId", source = "userId")
    RoleReport toReport(UsersEntity entity);

    UsersEntity toEntity(User domain);
}
