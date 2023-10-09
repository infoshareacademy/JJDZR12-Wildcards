package com.isa.wildcards.sevice;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.entity.Movie;
import com.isa.wildcards.repository.MovieRepository;
import com.isa.wildcards.utilities.SearchEngine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Service
public class SearchResultService {
    private final MovieRepository repository;
    private final MoviesSearchResultMapper moviesSearchResultMapper;

    public List<MovieDto> findMoviesBySearchQuery(String searchQuery) {
        List<Movie> movieList = repository.findAll();
        Map<Movie, Integer> foundMovies = SearchEngine.findMovies(movieList, searchQuery);
        return moviesSearchResultMapper.toMoviesDto(foundMovies);
    }

    public MovieDto getSearchSelectedResult(List<MovieDto> searchResultDto, UUID uuid) {
       return searchResultDto.stream().filter(e -> e.getUuid().equals(uuid))
               .findFirst()
               .orElseGet(MovieDto::new);
    }
}
