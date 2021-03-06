package com.kpj.homework.test;

import com.kpj.homework.HomeworkApplicationTests;
import com.kpj.homework.entity.ServiceEntity;
import com.kpj.homework.service.ServiceServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static com.kpj.homework.util.JsonMapperUtils.object2Json;
import static com.kpj.homework.util.ServiceEntityUtils.prepareListWithRandomServiceEntities;
import static com.kpj.homework.util.ServiceEntityUtils.prepareRandomServiceEntity;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class ControllerMvcTest extends HomeworkApplicationTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ServiceServiceImpl serviceMock;

    @Test
    void getService() throws Exception {
        ServiceEntity serviceEntity = prepareRandomServiceEntity();
        when(serviceMock.find(serviceEntity.getName())).thenReturn(Optional.of(serviceEntity));

        mvc.perform(get("/services/{name}", serviceEntity.getName()))
                .andExpect(status().isOk())
                .andExpect(content().json(object2Json(serviceEntity)));
    }

    @Test
    void getServiceNotFound() throws Exception {
        mvc.perform(get("/services/{name}", "blabla"))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Service not found"));
    }

    @Test
    void getAllServices() throws Exception {
        mvc.perform(get("/services/"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getAllServicesEmpty() throws Exception {
        List<ServiceEntity> serviceEntities = prepareListWithRandomServiceEntities();
        when(serviceMock.findAll()).thenReturn(serviceEntities);

        mvc.perform(get("/services/"))
                .andExpect(status().isOk())
                .andExpect(content().json(object2Json(serviceEntities)));
    }

    @Test
    void registerService() throws Exception {
        mvc.perform(post("/services/register"))
                .andExpect(status().isOk());

        verify(serviceMock, atLeast(1)).register();
    }
}
