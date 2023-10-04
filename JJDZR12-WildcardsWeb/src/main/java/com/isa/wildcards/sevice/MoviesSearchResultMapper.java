package com.isa.wildcards.sevice;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MoviesSearchResultMapper {
    public List<MovieDto> toMoviesDto(final Map<Movie, Integer> foundMoviesWithPriority) {
        List<Movie> sortedResults = foundMoviesWithPriority.entrySet()
                .stream()
                .sorted(Map.Entry.<Movie, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();

        List<MovieDto> movieDtoList = sortedResults.stream().map(movie -> {
            MovieDto movieDto = new MovieDto();
            movieDto.setId(movie.getId());
            movieDto.setTitle(movie.getTitle());
            movieDto.setYear(movie.getYear());
            movieDto.setRated(movie.getRated());
            movieDto.setReleased(movie.getReleased());
            movieDto.setRuntime(movie.getRuntime());
            movieDto.setGenre(movie.getGenre());
            movieDto.setDirector(movie.getDirector());
            movieDto.setWriter(movie.getWriter());
            movieDto.setPlot(movie.getPlot());
            movieDto.setLanguage(movie.getLanguage());
            movieDto.setCountry(movie.getCountry());
            movieDto.setAwards(movie.getAwards());
            return movieDto;
        }).collect(Collectors.toList());

        return movieDtoList;
    }
}
