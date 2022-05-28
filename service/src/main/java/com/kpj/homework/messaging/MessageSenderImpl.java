package com.kpj.homework.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public record MessageSenderImpl(AmqpTemplate rabbitTemplate, FanoutExchange exchange) implements MessageSender {

    public void send(String message) {
        rabbitTemplate.convertAndSend(exchange.getName(), "", message);
        log.info("Sent message: " + message);
    }
}