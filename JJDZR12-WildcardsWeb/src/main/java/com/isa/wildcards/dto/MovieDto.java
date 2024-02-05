package com.isa.wildcards.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class MovieDto {

    private UUID uuid;

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

    public MovieDto() {

    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "uuid=" + uuid +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", rated='" + rated + '\'' +
                ", released='" + released + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", awards='" + awards + '\'' +
                ", metascore='" + metascore + '\'' +
                '}';
    }
}
