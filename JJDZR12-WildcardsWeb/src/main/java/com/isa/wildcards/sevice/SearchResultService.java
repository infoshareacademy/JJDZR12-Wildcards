package com.isa.wildcards.sevice;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.entity.Movie;
import com.isa.wildcards.repository.MoviesRepository;
import com.isa.wildcards.utilities.SearchEngine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SearchResultService {
    private final MoviesRepository repository;
    private final MoviesSearchResultMapper moviesSearchResultMapper;

    public SearchResultService(final MoviesRepository repository, final MoviesSearchResultMapper moviesSearchResultMapper) {
        this.repository = repository;
        this.moviesSearchResultMapper = moviesSearchResultMapper;
    }

    public List<MovieDto> findMoviesBySearchQuery(String searchQuery) {
        var movieList = repository.findAll();
        Map<Movie, Integer> foundMovies = SearchEngine.findMovies(movieList, searchQuery);
        return moviesSearchResultMapper.toMoviesDto(foundMovies);
    }
}
