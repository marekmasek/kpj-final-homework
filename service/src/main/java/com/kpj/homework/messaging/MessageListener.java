package com.kpj.homework.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "#{queue.name}", id = "listener")
public interface MessageListener {

    @RabbitHandler
    void onMessage(String message);
}