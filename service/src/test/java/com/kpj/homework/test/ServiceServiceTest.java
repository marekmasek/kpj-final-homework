package com.kpj.homework.test;

import com.kpj.homework.HomeworkApplicationTests;
import com.kpj.homework.entity.ServiceEntity;
import com.kpj.homework.repository.ServiceRepository;
import com.kpj.homework.service.ServiceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static com.kpj.homework.util.ServiceEntityUtils.prepareRandomServiceEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

public class ServiceServiceTest extends HomeworkApplicationTests {

    @InjectMocks
    private ServiceServiceImpl service;
    @Mock
    private ServiceRepository repositoryMock;

    private ServiceEntity serviceEntity;

    @BeforeEach
    void setup() {
        serviceEntity = prepareRandomServiceEntity();
    }


    @Test
    void test() {
        given(repositoryMock.findByName(serviceEntity.getName())).willReturn(Optional.of(serviceEntity));

        Optional<ServiceEntity> returnedServiceEntity = service.find(serviceEntity.getName());

        assertTrue(returnedServiceEntity.isPresent());
        assertEquals(serviceEntity, returnedServiceEntity.get());
    }

}
