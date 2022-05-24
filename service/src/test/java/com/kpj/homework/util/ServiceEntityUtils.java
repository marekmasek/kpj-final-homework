package com.kpj.homework.util;

import com.kpj.homework.entity.ServiceEntity;

import java.time.OffsetDateTime;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.RandomUtils.nextLong;

public class ServiceEntityUtils {

    public static ServiceEntity prepareRandomServiceEntity() {
        return ServiceEntity.builder()
                .id(nextLong())
                .name("blabla" + nextInt())
                .port(String.valueOf(nextInt(1000, 99999)))
                .registerTime(OffsetDateTime.now())
                .build();
    }


}
