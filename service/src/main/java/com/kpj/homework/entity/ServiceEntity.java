package com.kpj.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String port;
    private OffsetDateTime registerTime;
}
