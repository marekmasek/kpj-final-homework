package com.kpj.homework.messaging;

import com.kpj.openapi.model.ServiceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange exchange;

    public void send(ServiceDTO serviceDTO) {
        rabbitTemplate.convertAndSend(exchange.getName(), "", serviceDTO);
        log.info("Sending Message to the Queue : " + serviceDTO.toString());
    }
}