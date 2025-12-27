package edu.raijin.insight.dimension.infrastructure.adapter.in.messaging.topic;

import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_KEY;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.StoryEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.model.Story;
import edu.raijin.insight.dimension.domain.usecase.CreateStoryUseCase;
import edu.raijin.insight.dimension.domain.usecase.UpdateStoryUseCase;
import edu.raijin.insight.dimension.infrastructure.adapter.in.messaging.mapper.StoryEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class StoryKafkaConsumerAdapter {

    private final CreateStoryUseCase create;
    private final UpdateStoryUseCase update;
    private final StoryEventMapper mapper;

    private void consumeCreatedStory(Story story) {
        create.create(story);
    }

    private void consumeUpdatedStory(Story story) {
        update.update(story.getStoryId(), story);
    }

    @KafkaListener(topics = "${kafka.topics.story-commands.topic}", properties = "${kafka.topics.story-commands.properties}", groupId = "insight-service")
    public void consumeStoryEvent(@Payload StoryEvent event, @Header(RECEIVED_KEY) String key) {
        Story story = mapper.toDomain(event);
        switch (key) {
            case "create" -> consumeCreatedStory(story);
            case "update" -> consumeUpdatedStory(story);
            case "delete" -> consumeUpdatedStory(story);
        }
    }
}

