package com.isa.wildcards.utilities;

import com.isa.wildcards.entity.Movie;
import org.junit.jupiter.api.Test;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

public class SearchEngineTest {


    @Test
    public void testFindMoviesFoundEntry() {

        Movie movie = getMovie();

        List<Movie> movies = List.of(movie);

        Map<Movie, Integer> result1 = SearchEngine.findMovies(movies, "Action");
        assertEquals(1, result1.size());
        assertTrue(result1.containsKey(movie));
        assertEquals(1, (int) result1.get(movie));
    }

    @Test
    public void testFindMoviesDidntFoundEntry() {

        Movie movie = getMovie();

        List<Movie> movies = List.of(movie);

        Map<Movie, Integer> result4 = SearchEngine.findMovies(movies, "Sci-Fi");
        assertTrue(result4.isEmpty());
    }


    private Movie getMovie() {
        Movie movie = new Movie();
        movie.setTitle("Movie1");
        movie.setGenre("Action");
        movie.setActors("Bill Clinton");
        movie.setCountry("USA");
        movie.setDirector("Adam Sandler");
        movie.setAwards("4");
        movie.setWriter("Boba Fet");
        movie.setRated("9/10");
        movie.setYear("2002");
        movie.setRuntime("120min");
        movie.setPlot("some plot");
        movie.setLanguage("Eng");
        movie.setMetascore("11");
        movie.setReleased("2002");
        return movie;
    }
}
