package com.kpj.homework.service;

import com.kpj.homework.data.CurrentService;
import com.kpj.homework.entity.ServiceEntity;
import com.kpj.homework.mapper.ServiceMapper;
import com.kpj.homework.messaging.MessageListener;
import com.kpj.homework.messaging.MessageSender;
import com.kpj.homework.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public record ServiceServiceImpl(ServiceRepository repository, CurrentService currentService, MessageSender messageSender,
                                 ServiceMapper mapper) implements ServiceService, MessageListener {

    @Override
    public Optional<ServiceEntity> find(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<ServiceEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public ServiceEntity getCurrentService() {
        return currentService.getEntity();
    }

    @Override
    public void register() {
        messageSender.send(mapper.toMessage(getCurrentService()));
    }

    @Override
    public void save(ServiceEntity service) {
        repository.save(service);
    }

    @Override
    public void onMessage(String message) {
        log.info("Received: " + message);
        ServiceEntity receivedService = mapper.toEntity(message);

        if (shouldRegisterNewService(receivedService)) {
            save(receivedService);
            register();
            log.info("New service was registered: {}", message);
        } else {
            log.info("Received service wasn't registered: {}", message);
        }
    }

    private boolean shouldRegisterNewService(ServiceEntity service) {
        return service != null
                && !getCurrentService().getName().equals(service.getName())
                && find(service.getName()).isEmpty();
    }
}
