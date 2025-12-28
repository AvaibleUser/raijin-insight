package edu.raijin.insight.fact.infrastructure.adapter.in.messaging.topic;

import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_KEY;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.BonusEvent;
import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.DiscountEvent;
import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.ExpenseEvent;
import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.IncomeEvent;
import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.PayrollEvent;
import edu.raijin.commons.infrastructure.adapter.messaging.event.finance.SuspensionEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.fact.domain.model.FinancialMovement;
import edu.raijin.insight.fact.domain.usecase.CreateFinancialMovementUseCase;
import edu.raijin.insight.fact.infrastructure.adapter.in.messaging.mapper.FinancialEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class FinancialKafkaConsumerAdapter {

    private final CreateFinancialMovementUseCase create;
    private final FinancialEventMapper mapper;

    private void consumeCreatedFinancialEvent(FinancialMovement movement) {
        create.create(movement);
    }

    @KafkaListener(topics = "${kafka.topics.bonus-commands.topic}", properties = "${kafka.topics.bonus-commands.properties}", groupId = "insight-service")
    public void consumeBonusEvent(@Payload BonusEvent event, @Header(RECEIVED_KEY) String key) {
        FinancialMovement movement = mapper.toDomain(event);
        consumeCreatedFinancialEvent(movement);
    }

    @KafkaListener(topics = "${kafka.topics.discount-commands.topic}", properties = "${kafka.topics.discount-commands.properties}", groupId = "insight-service")
    public void consumeDiscountEvent(@Payload DiscountEvent event, @Header(RECEIVED_KEY) String key) {
        FinancialMovement movement = mapper.toDomain(event);
        consumeCreatedFinancialEvent(movement);
    }

    @KafkaListener(topics = "${kafka.topics.expense-commands.topic}", properties = "${kafka.topics.expense-commands.properties}", groupId = "insight-service")
    public void consumeExpenseEvent(@Payload ExpenseEvent event, @Header(RECEIVED_KEY) String key) {
        FinancialMovement movement = mapper.toDomain(event);
        consumeCreatedFinancialEvent(movement);
    }

    @KafkaListener(topics = "${kafka.topics.income-commands.topic}", properties = "${kafka.topics.income-commands.properties}", groupId = "insight-service")
    public void consumeIncomeEvent(@Payload IncomeEvent event, @Header(RECEIVED_KEY) String key) {
        FinancialMovement movement = mapper.toDomain(event);
        consumeCreatedFinancialEvent(movement);
    }

    @KafkaListener(topics = "${kafka.topics.payroll-commands.topic}", properties = "${kafka.topics.payroll-commands.properties}", groupId = "insight-service")
    public void consumePayrollEvent(@Payload PayrollEvent event, @Header(RECEIVED_KEY) String key) {
        FinancialMovement movement = mapper.toDomain(event);
        consumeCreatedFinancialEvent(movement);
    }

    @KafkaListener(topics = "${kafka.topics.suspension-commands.topic}", properties = "${kafka.topics.suspension-commands.properties}", groupId = "insight-service")
    public void consumeSuspensionEvent(@Payload SuspensionEvent event, @Header(RECEIVED_KEY) String key) {
        FinancialMovement movement = mapper.toDomain(event);
        consumeCreatedFinancialEvent(movement);
    }
}
