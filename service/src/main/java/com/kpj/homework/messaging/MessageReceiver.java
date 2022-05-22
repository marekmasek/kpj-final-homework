package com.kpj.homework.messaging;

import com.kpj.homework.mapper.ServiceMapper;
import com.kpj.homework.repository.ServiceRepository;
import com.kpj.openapi.api.ServicesApiDelegate;
import com.kpj.openapi.model.ServiceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.kpj.homework.constant.ServiceDTOBuilder.CURRENT_SERVICE;

@Slf4j
@Component
@RabbitListener(queues = "#{queue.name}", id = "listener")
public class MessageReceiver {

    @Autowired
    private ServiceMapper serviceMapper;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServicesApiDelegate servicesApiDelegate;

    @RabbitHandler
    public void receiver(ServiceDTO service) {
        log.info("Received: " + service.toString());

        if (!CURRENT_SERVICE.equals(service) && serviceRepository.findByName(service.getName()).isEmpty()) {
            serviceRepository.save(serviceMapper.toEntity(service));

            servicesApiDelegate.register();
        }
    }
}