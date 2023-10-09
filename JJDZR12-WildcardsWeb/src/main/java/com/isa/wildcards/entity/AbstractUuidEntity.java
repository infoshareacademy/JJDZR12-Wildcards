package com.isa.wildcards.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@MappedSuperclass
@Setter
@Getter
public class AbstractUuidEntity extends AbstractEntity {
    private UUID uuid = UUID.randomUUID();
}
