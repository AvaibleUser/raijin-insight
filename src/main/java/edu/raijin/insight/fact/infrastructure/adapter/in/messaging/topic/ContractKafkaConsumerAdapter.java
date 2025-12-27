package edu.raijin.insight.fact.infrastructure.adapter.in.messaging.topic;

import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_KEY;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.type.EmployeeEventType;
import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.ContractEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.EmployeeEvent;
import edu.raijin.insight.fact.domain.usecase.CreateEmployeeEventUseCase;
import edu.raijin.insight.fact.infrastructure.adapter.in.messaging.mapper.ContractEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class ContractKafkaConsumerAdapter {

    private final CreateEmployeeEventUseCase create;
    private final ContractEventMapper mapper;

    private void consumeCreatedEmployeeEvent(EmployeeEvent employeeEvent) {
        create.create(employeeEvent);
    }

    @KafkaListener(topics = "${kafka.topics.contract-commands.topic}", properties = "${kafka.topics.contract-commands.properties}", groupId = "insight-service")
    public void consumeEmployeeEventEvent(@Payload ContractEvent event, @Header(RECEIVED_KEY) String key) {
        EmployeeEvent employeeEvent = mapper.toDomain(event);
        employeeEvent.setEventType(switch (event.getStatus()) {
            case TERMINATED -> EmployeeEventType.TERMINATION;
            default -> switch (key) {
                case "create" -> EmployeeEventType.HIRE;
                case "update" -> EmployeeEventType.UPDATE;
                default -> EmployeeEventType.TERMINATION;
            };
        });
        consumeCreatedEmployeeEvent(employeeEvent);
    }
}
