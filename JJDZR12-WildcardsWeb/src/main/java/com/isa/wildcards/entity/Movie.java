package com.isa.wildcards.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie extends AbstractUuidEntity{

    private String title;

    private String year;

    private String rated;

    private String released;

    private String runtime;

    private String genre;

    private String director;

    private String writer;

    private String actors;

    private String plot;

    private String language;

    private String country;

    private String awards;

    private String metascore;

    public Movie() {
    }

    public Movie(String title, String director) {
        this.title = title;
        this.director = director;
    }
}
