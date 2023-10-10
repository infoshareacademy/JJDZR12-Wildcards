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
}
