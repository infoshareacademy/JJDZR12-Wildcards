package com.isa.wildcards.entity;

import jakarta.persistence.*;
import lombok.*;

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
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final History history = (History) o;
        return Objects.equals(getSearchQuery(), history.getSearchQuery()) && Objects.equals(getUser(), history.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSearchQuery(), getUser());
    }
}
