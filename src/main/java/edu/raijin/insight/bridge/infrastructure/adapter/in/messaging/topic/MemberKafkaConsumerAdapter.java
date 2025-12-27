package edu.raijin.insight.bridge.infrastructure.adapter.in.messaging.topic;

import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_KEY;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.MemberEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.bridge.domain.model.Member;
import edu.raijin.insight.bridge.domain.usecase.CreateMemberUseCase;
import edu.raijin.insight.bridge.domain.usecase.DeleteMemberUseCase;
import edu.raijin.insight.bridge.infrastructure.adapter.in.messaging.mapper.MemberEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class MemberKafkaConsumerAdapter {

    private final CreateMemberUseCase create;
    private final DeleteMemberUseCase delete;
    private final MemberEventMapper mapper;

    private void consumeCreatedMember(Member member) {
        create.create(member);
    }

    private void consumeDeletedMember(Member member) {
        delete.delete(member.getProjectId(), member.getUserId());
    }

    @KafkaListener(topics = "${kafka.topics.member-commands.topic}", properties = "${kafka.topics.member-commands.properties}", groupId = "insight-service")
    public void consumeMemberEvent(@Payload MemberEvent event, @Header(RECEIVED_KEY) String key) {
        Member member = mapper.toDomain(event);
        switch (key) {
            case "create" -> consumeCreatedMember(member);
            case "delete" -> consumeDeletedMember(member);
        }
    }
}
