package edu.raijin.insight.audit.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.audit.domain.model.Snapshot;
import edu.raijin.insight.audit.infrastructure.adapter.out.persistence.entity.SnapshotsEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SnapshotEntityMapper {

    @Mapping(target = "actorId", source = "actor.userId")
    @Mapping(target = "fullName", source = "actor.fullName")
    @Mapping(target = "email", source = "actor.email")
    @Mapping(target = "role", source = "actor.role")
    Snapshot toDomain(SnapshotsEntity entity);

    @Mapping(target = "actor.userId", source = "actorId")
    SnapshotsEntity toEntity(Snapshot domain);
}
