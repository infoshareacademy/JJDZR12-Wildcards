package com.isa.wildcards.sevice;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.entity.Movie;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@UtilityClass
@Slf4j
public class MoviesSearchResultMapper {

    public List<MovieDto> toMoviesDto(final Map<Movie, Integer> foundMoviesWithPriority) {
        List<Movie> sortedResults = foundMoviesWithPriority.entrySet()
                .stream()
                .sorted(Map.Entry.<Movie, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();
        log.info("Sorting movies by priority.");
        return mapToDto(sortedResults);
    }

    private List<MovieDto> mapToDto(List<Movie> sortedMovies) {
        return sortedMovies.stream().map(movie -> {
            MovieDto movieDto = new MovieDto();
            movieDto.setUuid(movie.getUuid());
            movieDto.setTitle(movie.getTitle() != null ? movie.getTitle().toLowerCase() : null);
            movieDto.setYear(movie.getYear());
            movieDto.setRated(movie.getRated());
            movieDto.setReleased(movie.getReleased());
            movieDto.setRuntime(movie.getRuntime());
            movieDto.setGenre(movie.getGenre());
            movieDto.setActors(movie.getActors());
            movieDto.setMetascore(movie.getMetascore());
            movieDto.setDirector(movie.getDirector());
            movieDto.setWriter(movie.getWriter());
            movieDto.setPlot(movie.getPlot());
            movieDto.setLanguage(movie.getLanguage());
            movieDto.setCountry(movie.getCountry());
            movieDto.setAwards(movie.getAwards());
            log.info("Mapping movies to DTO.");
            return movieDto;
        }).toList();
    }
}