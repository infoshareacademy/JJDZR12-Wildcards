package com.isa.wildcards.service;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.entity.Movie;
import com.isa.wildcards.repository.MovieRepository;
import com.isa.wildcards.sevice.MoviesSearchResultMapper;
import com.isa.wildcards.sevice.SearchResultService;
import com.isa.wildcards.utilities.SearchEngine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchResultServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private SearchEngine searchEngine;

    @Mock
    private MoviesSearchResultMapper moviesSearchResultMapper;

    @InjectMocks
    private SearchResultService searchResultService;

    @Test
    void findMoviesBySearchQueryTest() {

        String searchQuery = "Action";
        List<Movie> movieList = Arrays.asList(
                createSampleMovie("Inception", "2010", "PG-13", "16 Jul 2010", "148 min", "Action, Adventure, Sci-Fi", "Christopher Nolan", "Christopher Nolan, Jonathan Nolan", "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.", "English", "USA, UK", "Won 4 Oscars. Another 152 wins & 217 nominations.", "74"),
                createSampleMovie("The Dark Knight", "2008", "PG-13", "18 Jul 2008", "152 min", "Action, Crime, Drama", "Christopher Nolan", "Jonathan Nolan (screenplay), Christopher Nolan (screenplay)", "Christian Bale, Heath Ledger, Aaron Eckhart", "When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "English", "USA, UK", "Won 2 Oscars. Another 152 wins & 155 nominations.", "84")
        );

        Map<Movie, Integer> foundMovies = new HashMap<>();
        foundMovies.put(movieList.get(0), 10);
        foundMovies.put(movieList.get(1), 5);

        List<MovieDto> expectedMovieDtos = Arrays.asList(
                createSampleMovieDto("Inception", "2010", "PG-13", "16 Jul 2010", "148 min", "Action, Adventure, Sci-Fi", "Christopher Nolan", "Christopher Nolan, Jonathan Nolan", "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.", "English", "USA, UK", "Won 4 Oscars. Another 152 wins & 217 nominations.", "74"),
                createSampleMovieDto("The Dark Knight", "2008", "PG-13", "18 Jul 2008", "152 min", "Action, Crime, Drama", "Christopher Nolan", "Jonathan Nolan (screenplay), Christopher Nolan (screenplay)", "Christian Bale, Heath Ledger, Aaron Eckhart", "When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "English", "USA, UK", "Won 2 Oscars. Another 152 wins & 155 nominations.", "84")
        );

        when(movieRepository.findAll()).thenReturn(movieList);
        when(searchEngine.findMovies(anyList(), anyString())).thenReturn(foundMovies);
        when(moviesSearchResultMapper.toMoviesDto(foundMovies)).thenReturn(expectedMovieDtos);

        List<MovieDto> result = searchResultService.findMoviesBySearchQuery(searchQuery);

        assertEquals(expectedMovieDtos, result);
        verify(movieRepository, times(1)).findAll();
        verify(searchEngine, times(1)).findMovies(movieList, searchQuery);
        verify(moviesSearchResultMapper, times(1)).toMoviesDto(foundMovies);
    }

    private Movie createSampleMovie(String title, String year, String rated, String released, String runtime, String genre,
                                    String director, String writer, String actors, String plot, String language, String country,
                                    String awards, String metascore) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setYear(year);
        movie.setRated(rated);
        movie.setReleased(released);
        movie.setRuntime(runtime);
        movie.setGenre(genre);
        movie.setDirector(director);
        movie.setWriter(writer);
        movie.setActors(actors);
        movie.setPlot(plot);
        movie.setLanguage(language);
        movie.setCountry(country);
        movie.setAwards(awards);
        movie.setMetascore(metascore);
        return movie;
    }

    private MovieDto createSampleMovieDto(String title, String year, String rated, String released, String runtime, String genre,
                                          String director, String writer, String actors, String plot, String language, String country,
                                          String awards, String metascore) {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle(title);
        movieDto.setYear(year);
        movieDto.setRated(rated);
        movieDto.setReleased(released);
        movieDto.setRuntime(runtime);
        movieDto.setGenre(genre);
        movieDto.setDirector(director);
        movieDto.setWriter(writer);
        movieDto.setActors(actors);
        movieDto.setPlot(plot);
        movieDto.setLanguage(language);
        movieDto.setCountry(country);
        movieDto.setAwards(awards);
        movieDto.setMetascore(metascore);
        return movieDto;
    }
}