package com.kpj.homework.test;

import com.kpj.homework.HomeworkApplicationTests;
import com.kpj.homework.controller.ServicesApiDelegateImpl;
import com.kpj.homework.entity.ServiceEntity;
import com.kpj.homework.mapper.ServiceMapper;
import com.kpj.homework.service.ServiceService;
import com.kpj.openapi.model.ServiceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.kpj.homework.util.ServiceEntityUtils.prepareRandomServiceEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class ServiceControllerTest extends HomeworkApplicationTests {

    @InjectMocks
    private ServicesApiDelegateImpl controller;
    @Mock
    private ServiceService serviceMock;
    @Mock
    private ServiceMapper serviceMapper;

    private ServiceEntity serviceEntity;

    @BeforeEach
    void setup() {
        serviceEntity = prepareRandomServiceEntity();
    }

    @Test
    void test() {
        given(serviceMock.find(serviceEntity.getName())).willReturn(Optional.of(serviceEntity));

        ResponseEntity<ServiceDTO> responseEntity = controller.getService(serviceEntity.getName());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void test2() {
        given(serviceMock.find(anyString())).willReturn(Optional.empty());

        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> controller.getService("blabla"));

        assertEquals(NOT_FOUND, thrown.getStatus());
        assertEquals("Service not found", thrown.getReason());
    }

}
