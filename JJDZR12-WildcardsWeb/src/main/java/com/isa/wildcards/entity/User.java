package com.isa.wildcards.entity;

import jakarta.persistence.*;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User extends AbstractEntity implements UserDetails {

    @Column(unique = true)
    private String username;

    private String password;

    @Column(name = "user_role",columnDefinition = "VARCHAR(255) default 'USER'")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<History> historyId;

    public User(final String username, final String password, final UserRole userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

