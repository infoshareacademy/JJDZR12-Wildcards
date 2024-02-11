package com.isa.wildcards.service;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.entity.History;
import com.isa.wildcards.entity.Movie;
import com.isa.wildcards.entity.User;
import com.isa.wildcards.repository.HistoryRepository;
import com.isa.wildcards.repository.MovieRepository;
import com.isa.wildcards.repository.UserRepository;
import com.isa.wildcards.sevice.MoviesSearchResultMapper;
import com.isa.wildcards.sevice.SearchResultService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchResultServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private SearchResultService searchResultService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HistoryRepository historyRepository;

    @Test
    void shouldFindMoviesBySearchQuery() {

        String searchQuery = "Action";
        List<Movie> movieList = Arrays.asList(
                createSampleMovie("Inception", "2010", "PG-13", "16 Jul 2010", "148 min", "Action, Adventure, Sci-Fi", "Christopher Nolan", "Christopher Nolan, Jonathan Nolan", "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.", "English", "USA, UK", "Won 4 Oscars. Another 152 wins & 217 nominations.", "74"),
                createSampleMovie("The Lord of the Rings: The Fellowship of the Ring", "2001", "PG-13", "19 Dec 2001", "178 min", "Adventure, Drama, Fantasy", "Peter Jackson", "J.R.R. Tolkien (novel), Fran Walsh (screenplay)", "Elijah Wood, Ian McKellen, Orlando Bloom, Sean Bean", "A young hobbit, Frodo, is entrusted with a powerful ring to save Middle-earth from the dark lord Sauron's grasp.", "English, Sindarin", "New Zealand, USA", "Won 4 Oscars. Another 118 wins & 127 nominations.", "92"),
                createSampleMovie("The Dark Knight", "2008", "PG-13", "18 Jul 2008", "152 min", "Action, Crime, Drama", "Christopher Nolan", "Jonathan Nolan (screenplay), Christopher Nolan (screenplay)", "Christian Bale, Heath Ledger, Aaron Eckhart", "When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "English", "USA, UK", "Won 2 Oscars. Another 152 wins & 155 nominations.", "84")
        );

        Map<Movie, Integer> foundMovies = new HashMap<>();
        foundMovies.put(movieList.get(0), 1);
        foundMovies.put(movieList.get(2), 1);

        List<MovieDto> expected = MoviesSearchResultMapper.toMoviesDto(foundMovies);

        when(movieRepository.findAll()).thenReturn(movieList);

        List<MovieDto> result = searchResultService.findMoviesBySearchQuery(searchQuery);

        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }


    @Test
    void shouldReturnEmptyListWhenSearchQueryIsEmpty() {

        String emptySearchQuery = "xxx";
        List<Movie> movieList = Arrays.asList(
                createSampleMovie("Inception", "2010", "PG-13", "16 Jul 2010", "148 min", "Action, Adventure, Sci-Fi", "Christopher Nolan", "Christopher Nolan, Jonathan Nolan", "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.", "English", "USA, UK", "Won 4 Oscars. Another 152 wins & 217 nominations.", "74"),
                createSampleMovie("The Lord of the Rings: The Fellowship of the Ring", "2001", "PG-13", "19 Dec 2001", "178 min", "Adventure, Drama, Fantasy", "Peter Jackson", "J.R.R. Tolkien (novel), Fran Walsh (screenplay)", "Elijah Wood, Ian McKellen, Orlando Bloom, Sean Bean", "A young hobbit, Frodo, is entrusted with a powerful ring to save Middle-earth from the dark lord Sauron's grasp.", "English, Sindarin", "New Zealand, USA", "Won 4 Oscars. Another 118 wins & 127 nominations.", "92"),
                createSampleMovie("The Dark Knight", "2008", "PG-13", "18 Jul 2008", "152 min", "Action, Crime, Drama", "Christopher Nolan", "Jonathan Nolan (screenplay), Christopher Nolan (screenplay)", "Christian Bale, Heath Ledger, Aaron Eckhart", "When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "English", "USA, UK", "Won 2 Oscars. Another 152 wins & 155 nominations.", "84")
        );

        when(movieRepository.findAll()).thenReturn(movieList);

        List<MovieDto> result = searchResultService.findMoviesBySearchQuery(emptySearchQuery);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    @Test
    void testSaveHistory() {

        User user = new User();
        user.setUsername("testUser");
        String searchQuery = "sampleSearchQuery";

        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        searchResultService.saveHistory(searchQuery, user);

        verify(userRepository, times(1)).findByUsername(user.getUsername());

        verify(historyRepository, times(1)).save(any(History.class));
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
}