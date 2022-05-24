package com.kpj.homework.controller;

import com.kpj.homework.mapper.ServiceMapper;
import com.kpj.homework.service.ServiceService;
import com.kpj.openapi.api.ServicesApiDelegate;
import com.kpj.openapi.model.ServiceDTO;
import lombok.SneakyThrows;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
public record ServicesApiDelegateImpl(ServiceService service, ServiceMapper mapper) implements ServicesApiDelegate {

    @Override
    public ResponseEntity<ServiceDTO> getService(String name) {
        return ResponseEntity.ok(mapper.toDTO(service.find(name)
                                                      .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Service not found"))));
    }

    @Override
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toDTO).toList());
    }

    @Override
    public ResponseEntity<ServiceDTO> getCurrent() {
        return ResponseEntity.ok(mapper.toDTO(service.getCurrentService()));
    }

    @EventListener(ApplicationReadyEvent.class)
    @SneakyThrows
    @Override
    public ResponseEntity<Void> register() {
        service.register();
        return ResponseEntity.ok().build();
    }

}
