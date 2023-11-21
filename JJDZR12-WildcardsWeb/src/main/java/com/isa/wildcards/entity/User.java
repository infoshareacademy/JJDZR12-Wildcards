package com.isa.wildcards.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity {

    private String username;

    private String password;

}
