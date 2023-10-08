package com.isa.wildcards.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    private final UUID id;
    private String username;
    private String password;

    public User(final String username, final String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
    }

    public User() {
        this.id = UUID.randomUUID();
    }

    public UUID id() {
        return id;
    }

    public String username() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String password() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
