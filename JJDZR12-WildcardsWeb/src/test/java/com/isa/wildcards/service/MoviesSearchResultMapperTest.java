package com.isa.wildcards.service;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.entity.Movie;
import com.isa.wildcards.sevice.MoviesSearchResultMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MoviesSearchResultMapperTest {

    @Test
    void shouldReturnMoviesList() {
        //given
        Map<Movie, Integer> foundMoviesWithPriority = new HashMap<>();
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        foundMoviesWithPriority.put(movie1, 3);
        foundMoviesWithPriority.put(movie2, 5);

        //when
        List<MovieDto> result = MoviesSearchResultMapper.toMoviesDto(foundMoviesWithPriority);

        //then
        assertThat(result).hasSize(2);
    }

    @Test
    void shouldReturnEmptyListWhenParameterIsEmpty() {
        //given
        Map<Movie, Integer> foundMoviesWithPriority = new HashMap<>();

        //when
        List<MovieDto> result = MoviesSearchResultMapper.toMoviesDto(foundMoviesWithPriority);

        //then
        assertThat(result).isEmpty();
    }
}