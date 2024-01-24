package com.isa.wildcards.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Table(name = "history")
@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class History extends AbstractEntity {
    @Column(name = "query")
    private String searchQuery;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return searchQuery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(searchQuery, history.searchQuery) &&
                Objects.equals(user, history.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchQuery, user);
    }
}
