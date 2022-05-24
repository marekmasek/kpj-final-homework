package com.kpj.homework.data;

import com.kpj.homework.entity.ServiceEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CurrentService {

    @Getter
    @Value("${service.name}")
    private String name;

    @Value("${service.exposed.port}")
    private String port;

    public ServiceEntity getEntity() {
        return ServiceEntity.builder()
                .name(name)
                .port(port)
                .build();
    }
}
