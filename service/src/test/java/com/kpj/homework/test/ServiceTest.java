package com.kpj.homework.test;

import com.kpj.homework.HomeworkApplicationTests;
import com.kpj.homework.entity.ServiceEntity;
import com.kpj.homework.repository.ServiceRepository;
import com.kpj.homework.service.ServiceServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static com.kpj.homework.util.ServiceEntityUtils.prepareListWithRandomServiceEntities;
import static com.kpj.homework.util.ServiceEntityUtils.prepareRandomServiceEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

public class ServiceTest extends HomeworkApplicationTests {

    @InjectMocks
    private ServiceServiceImpl service;

    @Mock
    private ServiceRepository repositoryMock;

    @Test
    void findService() {
        ServiceEntity serviceEntity = prepareRandomServiceEntity();
        given(repositoryMock.findByName(serviceEntity.getName())).willReturn(Optional.of(serviceEntity));

        Optional<ServiceEntity> returnedServiceEntity = service.find(serviceEntity.getName());

        assertTrue(returnedServiceEntity.isPresent(), "Returned service entity is not present.");
        assertEquals(serviceEntity, returnedServiceEntity.get(), "Returned service entity is not as expected.");
    }

    @Test
    void findAllServices() {
        List<ServiceEntity> serviceEntities = prepareListWithRandomServiceEntities();
        given(repositoryMock.findAll()).willReturn(serviceEntities);

        List<ServiceEntity> returnedServiceEntities = service.findAll();

        assertEquals(serviceEntities, returnedServiceEntities, "Returned service entities are not as expected.");
    }

}
