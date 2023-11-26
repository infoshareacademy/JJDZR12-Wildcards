package com.isa.wildcards.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
}
