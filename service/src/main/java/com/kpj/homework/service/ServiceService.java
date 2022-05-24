package com.kpj.homework.service;

import com.kpj.homework.entity.ServiceEntity;

import java.util.List;
import java.util.Optional;

public interface ServiceService {

    Optional<ServiceEntity> find(String name);

    List<ServiceEntity> findAll();

    ServiceEntity getCurrentService();

    void register();

    void save(ServiceEntity service);
}
