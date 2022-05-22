package com.kpj.homework.constant;

import com.kpj.openapi.model.ServiceDTO;

import java.time.OffsetDateTime;

public class ServiceDTOBuilder {

    public static final ServiceDTO CURRENT_SERVICE = ServiceDTO.builder()
            //.id(123L)
            .name("marekmasek")
            .port("22956")
            .registerTime(OffsetDateTime.now())
            .build();
}
