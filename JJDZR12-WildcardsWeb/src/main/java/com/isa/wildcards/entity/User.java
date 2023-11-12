package com.isa.wildcards.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User extends AbstractEntity {


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;


}
