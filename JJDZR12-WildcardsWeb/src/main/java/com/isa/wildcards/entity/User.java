package com.isa.wildcards.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends AbstractUuidEntity {
    
    private String username;
    private String password;
}
