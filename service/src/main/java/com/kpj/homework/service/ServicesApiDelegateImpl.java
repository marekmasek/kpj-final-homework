package com.kpj.homework.service;

import com.kpj.homework.mapper.ServiceMapper;
import com.kpj.homework.messaging.MessageSender;
import com.kpj.homework.repository.ServiceRepository;
import com.kpj.openapi.api.ServicesApiDelegate;
import com.kpj.openapi.model.ServiceDTO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.kpj.homework.constant.ServiceDTOBuilder.CURRENT_SERVICE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ServicesApiDelegateImpl implements ServicesApiDelegate {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceMapper serviceMapper;

    @Autowired
    private MessageSender messageSender;

    @Override
    public ResponseEntity<ServiceDTO> getService(String name) {
        return ResponseEntity.ok(serviceMapper.toDTO(serviceRepository.findByName(name)
                                                             .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Service not found"))));
    }

    @Override
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        return ResponseEntity.ok(serviceRepository.findAll().stream().map(serviceMapper::toDTO).toList());
    }

    @Override
    public ResponseEntity<ServiceDTO> getCurrent() {
        return ResponseEntity.ok(CURRENT_SERVICE);
    }

    @EventListener(ApplicationReadyEvent.class)
    @SneakyThrows
    @Override
    public ResponseEntity<Void> register() {
        messageSender.send(CURRENT_SERVICE);
        return ResponseEntity.ok().build();
    }

}
