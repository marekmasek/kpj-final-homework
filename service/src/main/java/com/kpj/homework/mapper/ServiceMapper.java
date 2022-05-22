package com.kpj.homework.mapper;

import com.kpj.homework.entity.ServiceEntity;
import com.kpj.openapi.model.ServiceDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceDTO toDTO(ServiceEntity serviceEntity);

    @InheritInverseConfiguration
    ServiceEntity toEntity(ServiceDTO serviceDTO);
}
