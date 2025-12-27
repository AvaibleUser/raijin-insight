package edu.raijin.insight.dimension.infrastructure.adapter.in.messaging.topic;

import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_KEY;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.identity.UserEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.model.User;
import edu.raijin.insight.dimension.domain.usecase.CreateUserUseCase;
import edu.raijin.insight.dimension.domain.usecase.UpdateUserUseCase;
import edu.raijin.insight.dimension.infrastructure.adapter.in.messaging.mapper.UserEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class UserKafkaConsumerAdapter {

    private final CreateUserUseCase create;
    private final UpdateUserUseCase update;
    private final UserEventMapper mapper;

    private void consumeCreatedUser(User user) {
        create.create(user);
    }

    private void consumeUpdatedUser(User user) {
        update.update(user.getUserId(), user);
    }

    @KafkaListener(topics = "${kafka.topics.user-commands.topic}", properties = "${kafka.topics.user-commands.properties}", groupId = "insight-service")
    public void consumeUserEvent(@Payload UserEvent event, @Header(RECEIVED_KEY) String key) {
        User user = mapper.toDomain(event);
        switch (key) {
            case "create" -> consumeCreatedUser(user);
            case "update" -> consumeUpdatedUser(user);
            case "delete" -> consumeUpdatedUser(user);
        }
    }
}
