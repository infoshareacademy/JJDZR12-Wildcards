package com.isa.wildcards.entity;

import jakarta.persistence.*;

import lombok.*;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }
}
