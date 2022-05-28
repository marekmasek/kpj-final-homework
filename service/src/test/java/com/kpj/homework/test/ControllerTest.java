package com.kpj.homework.test;

import com.kpj.homework.HomeworkApplicationTests;
import com.kpj.homework.controller.ServicesApiDelegateImpl;
import com.kpj.homework.entity.ServiceEntity;
import com.kpj.homework.mapper.ServiceMapper;
import com.kpj.homework.service.ServiceService;
import com.kpj.openapi.model.ServiceDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static com.kpj.homework.util.ServiceEntityUtils.prepareListWithRandomServiceEntities;
import static com.kpj.homework.util.ServiceEntityUtils.prepareRandomServiceEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class ControllerTest extends HomeworkApplicationTests {

    @InjectMocks
    private ServicesApiDelegateImpl controller;

    @Mock
    private ServiceService serviceMock;

    @Mock
    private ServiceMapper mapper;

    @Test
    void getService() {
        ServiceEntity serviceEntity = prepareRandomServiceEntity();
        given(serviceMock.find(serviceEntity.getName())).willReturn(Optional.of(serviceEntity));

        ResponseEntity<ServiceDTO> responseEntity = controller.getService(serviceEntity.getName());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "Returned status code is not as expected.");
        assertEquals(mapper.toDTO(serviceEntity), responseEntity.getBody(), "Returned body is not as expected.");
    }

    @Test
    void getServiceNotFound() {
        given(serviceMock.find(anyString())).willReturn(Optional.empty());

        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> controller.getService("blabla"));

        assertEquals(NOT_FOUND, thrown.getStatus(), "Returned status code is not as expected.");
        assertEquals("Service not found", thrown.getReason(), "Returned error message is not as expected.");
    }

    @Test
    void getAllServices() {
        List<ServiceEntity> serviceEntities = prepareListWithRandomServiceEntities();
        given(serviceMock.findAll()).willReturn(serviceEntities);

        ResponseEntity<List<ServiceDTO>> responseEntities = controller.getAllServices();

        assertEquals(HttpStatus.OK, responseEntities.getStatusCode(), "Returned status code is not as expected.");
        assertEquals(serviceEntities.stream().map(mapper::toDTO).toList(), responseEntities.getBody(), "Returned body is not as expected.");
    }

    @Test
    void register() {
        controller.register();

        verify(serviceMock, times(1)).register();
    }

}
