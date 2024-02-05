package com.isa.wildcards.entity;

import jakarta.persistence.*;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User extends AbstractEntity {

    @Column(unique = true)
    private String username;

    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<History> historyId;

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
}
