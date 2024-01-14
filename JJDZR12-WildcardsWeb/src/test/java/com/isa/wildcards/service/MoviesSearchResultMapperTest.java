package com.isa.wildcards.service;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.entity.Movie;
import com.isa.wildcards.sevice.MoviesSearchResultMapper;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class MoviesSearchResultMapperTest {

    @Test
    public void testToMoviesDto() {

        MoviesSearchResultMapper mapper = new MoviesSearchResultMapper();
        Map<Movie, Integer> foundMoviesWithPriority = new HashMap<>();
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        foundMoviesWithPriority.put(movie1, 3);
        foundMoviesWithPriority.put(movie2, 5);

        List<MovieDto> result = mapper.toMoviesDto(foundMoviesWithPriority);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testToMoviesDtoWithEmptyInput() {

        MoviesSearchResultMapper mapper = new MoviesSearchResultMapper();
        Map<Movie, Integer> foundMoviesWithPriority = new HashMap<>();

        List<MovieDto> result = mapper.toMoviesDto(foundMoviesWithPriority);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testToMoviesDtoWithNullInput() {

        MoviesSearchResultMapper mapper = new MoviesSearchResultMapper();

        List<MovieDto> result = null;

        assertThrows(NullPointerException.class, () -> mapper.toMoviesDto(null));
    }
}
