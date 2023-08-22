package com.isa.wildcards.searchEngine;

public class SearchResult {
    private String title;
    private int year;
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
    private int metascore;

    //prosty konstruktor do testów/ewentualnie do wyswietlania krótkiego info
    public SearchResult(String title, String runtime, String actors, String plot, String language) {
        this.title = title;
        this.runtime = runtime;
        this.actors = actors;
        this.plot = plot;
        this.language = language;
    }

    //pełny konstruktor do wyświetlania pełnej zawartości wpisu

    //TODO docelowo zamienić na konwersje przy użyciu zewnętrznej biblioteki np. Jackson
    public SearchResult(String title, int year, String rated, String released,
                        String runtime, String genre, String director, String writer,
                        String actors, String plot, String language, String country,
                        String awards, int metascore) {
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.metascore = metascore;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getRated() {
        return rated;
    }

    public String getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getAwards() {
        return awards;
    }

    public int getMetascore() {
        return metascore;
    }
}
