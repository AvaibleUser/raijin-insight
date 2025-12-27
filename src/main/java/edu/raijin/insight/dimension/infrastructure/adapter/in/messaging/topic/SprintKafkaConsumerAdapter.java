package edu.raijin.insight.dimension.infrastructure.adapter.in.messaging.topic;

import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_KEY;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.SprintEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.model.Sprint;
import edu.raijin.insight.dimension.domain.usecase.CreateSprintUseCase;
import edu.raijin.insight.dimension.domain.usecase.UpdateSprintUseCase;
import edu.raijin.insight.dimension.infrastructure.adapter.in.messaging.mapper.SprintEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class SprintKafkaConsumerAdapter {

    private final CreateSprintUseCase create;
    private final UpdateSprintUseCase update;
    private final SprintEventMapper mapper;

    private void consumeCreatedSprint(Sprint sprint) {
        create.create(sprint);
    }

    private void consumeUpdatedSprint(Sprint sprint) {
        update.update(sprint.getSprintId(), sprint);
    }

    @KafkaListener(topics = "${kafka.topics.sprint-commands.topic}", properties = "${kafka.topics.sprint-commands.properties}", groupId = "insight-service")
    public void consumeSprintEvent(@Payload SprintEvent event, @Header(RECEIVED_KEY) String key) {
        Sprint sprint = mapper.toDomain(event);
        switch (key) {
            case "create" -> consumeCreatedSprint(sprint);
            case "update" -> consumeUpdatedSprint(sprint);
            case "delete" -> consumeUpdatedSprint(sprint);
        }
    }
}

