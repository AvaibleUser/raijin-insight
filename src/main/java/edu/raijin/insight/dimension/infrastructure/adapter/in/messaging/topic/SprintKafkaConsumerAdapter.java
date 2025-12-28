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
import edu.raijin.insight.fact.domain.model.SprintStatus;
import edu.raijin.insight.fact.domain.usecase.CreateSprintStatusUseCase;
import edu.raijin.insight.fact.domain.usecase.UpdateSprintStatusUseCase;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class SprintKafkaConsumerAdapter {

    private final CreateSprintUseCase create;
    private final UpdateSprintUseCase update;
    private final CreateSprintStatusUseCase createStatus;
    private final UpdateSprintStatusUseCase updateStatus;
    private final SprintEventMapper mapper;

    private void consumeCreatedSprint(Sprint sprint, SprintStatus status) {
        create.create(sprint);
        createStatus.create(status);
    }

    private void consumeUpdatedSprint(Sprint sprint, SprintStatus status) {
        update.update(sprint.getSprintId(), sprint);
        updateStatus.update(status);
    }

    @KafkaListener(topics = "${kafka.topics.sprint-commands.topic}", properties = "${kafka.topics.sprint-commands.properties}", groupId = "insight-service")
    public void consumeSprintEvent(@Payload SprintEvent event, @Header(RECEIVED_KEY) String key) {
        Sprint sprint = mapper.toDomain(event);
        SprintStatus status = mapper.toDomainStatus(event);
        switch (key) {
            case "create" -> consumeCreatedSprint(sprint, status);
            case "update" -> consumeUpdatedSprint(sprint, status);
            case "delete" -> consumeUpdatedSprint(sprint, status);
        }
    }
}
