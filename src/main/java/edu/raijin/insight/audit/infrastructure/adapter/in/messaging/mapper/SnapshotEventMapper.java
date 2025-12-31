package edu.raijin.insight.audit.infrastructure.adapter.in.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.ProjectEvent;
import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.SprintEvent;
import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.StoryEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.audit.domain.model.Snapshot;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SnapshotEventMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aggregateId", source = "id")
    @Mapping(target = "eventType", source = "audit.eventType")
    @Mapping(target = "actorId", source = "audit.actorId")
    @Mapping(target = "occurredAt", source = "audit.occurredAt")
    @Mapping(target = "description", source = "audit.description")
    @Mapping(target = "details", source = "event")
    Snapshot toDomain(StoryEvent event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aggregateId", source = "id")
    @Mapping(target = "eventType", source = "audit.eventType")
    @Mapping(target = "actorId", source = "audit.actorId")
    @Mapping(target = "occurredAt", source = "audit.occurredAt")
    @Mapping(target = "description", source = "audit.description")
    @Mapping(target = "details", source = "event")
    Snapshot toDomain(SprintEvent event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aggregateId", source = "id")
    @Mapping(target = "eventType", source = "audit.eventType")
    @Mapping(target = "actorId", source = "audit.actorId")
    @Mapping(target = "occurredAt", source = "audit.occurredAt")
    @Mapping(target = "description", source = "audit.description")
    @Mapping(target = "details", source = "event")
    Snapshot toDomain(ProjectEvent event);

    default ObjectMapper mapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    default HashMap<String, Object> toMap(StoryEvent event) {
        ObjectMapper mapper = mapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        return mapper.convertValue(event, typeFactory.constructMapType(Map.class, String.class, Object.class));
    }

    default HashMap<String, Object> toMap(SprintEvent event) {
        ObjectMapper mapper = mapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        return mapper.convertValue(event, typeFactory.constructMapType(Map.class, String.class, Object.class));
    }

    default HashMap<String, Object> toMap(ProjectEvent event) {
        ObjectMapper mapper = mapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        return mapper.convertValue(event, typeFactory.constructMapType(Map.class, String.class, Object.class));
    }
}
