package edu.raijin.insight.bridge.infrastructure.adapter.in.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.MemberEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.bridge.domain.model.Member;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberEventMapper {

    Member toDomain(MemberEvent event);
}
