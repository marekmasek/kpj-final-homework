package com.kpj.homework.mapper;

import com.kpj.homework.entity.ServiceEntity;
import com.kpj.openapi.model.ServiceDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;

import static java.lang.String.format;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    Logger log = LoggerFactory.getLogger(ServiceMapper.class);

    ServiceDTO toDTO(ServiceEntity serviceEntity);

    @InheritInverseConfiguration
    ServiceEntity toEntity(ServiceDTO serviceDTO);

    default @Nullable
    ServiceEntity toEntity(String message) {
        String[] splitMessage = message.split(";");

        if (splitMessage.length != 2 || !splitMessage[1].matches("[0-9]+")) {
            log.warn("Received message is not valid. \nReceived message: {} \nExpected message format: name;port", message);
            return null;
        }

        return ServiceEntity.builder()
                .name(splitMessage[0])
                .port(splitMessage[1])
                .registerTime(OffsetDateTime.now())
                .build();
    }

    default String toMessage(ServiceEntity service) {
        return format("%s;%s", service.getName(), service.getPort());
    }
}
