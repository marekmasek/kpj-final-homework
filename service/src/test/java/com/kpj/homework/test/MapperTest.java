package com.kpj.homework.test;

import com.kpj.homework.HomeworkApplicationTests;
import com.kpj.homework.entity.ServiceEntity;
import com.kpj.homework.mapper.ServiceMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(OutputCaptureExtension.class)
public class MapperTest extends HomeworkApplicationTests {

    @Autowired
    private ServiceMapper mapper;

    @ParameterizedTest
    @ValueSource(strings = {"blabla2555", "blabla;bla"})
    void invalidMessageToEntityMapper(String message, CapturedOutput output) {
        ServiceEntity service = mapper.toEntity(message);

        assertNull(service, "Invalid message wasn't mapped to ServiceEntity=null");
        assertTrue(output.getOut().contains("Received message is not valid."));
    }

    @Test
    void validMessageToEntityMapper() {
        String name = "blabla";
        String port = "12345";
        ServiceEntity service = mapper.toEntity(name + ";" + port);

        assertNotNull(service, "Valid message wasn't mapped correctly, it was mapped to ServiceEntity=null");
        assertEquals(name, service.getName(), "Valid message service name, wasn't mapped correctly to ServiceEntity");
        assertEquals(port, service.getPort(), "Valid message service port, wasn't mapped correctly to ServiceEntity");
    }

}
