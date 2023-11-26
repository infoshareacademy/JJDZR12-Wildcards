package com.isa.wildcards.sevice;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.entity.History;
import com.isa.wildcards.entity.Movie;
import com.isa.wildcards.entity.User;
import com.isa.wildcards.repository.HistoryRepository;
import com.isa.wildcards.repository.MovieRepository;
import com.isa.wildcards.repository.UserRepository;
import com.isa.wildcards.utilities.SearchEngine;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SearchResultService {
    private final MovieRepository movieRepository;
    private final HistoryRepository historyRepository;
    private final MoviesSearchResultMapper moviesSearchResultMapper;
    private final UserRepository userRepository;

    public List<MovieDto> findMoviesBySearchQuery(String searchQuery) {
        List<Movie> movieList = movieRepository.findAll();
        Map<Movie, Integer> foundMovies = SearchEngine.findMovies(movieList, searchQuery);
        return moviesSearchResultMapper.toMoviesDto(foundMovies);
    }

    public MovieDto getSearchSelectedResult(List<MovieDto> searchResultDto, UUID uuid) {
       return searchResultDto.stream()
               .filter(e -> e.getUuid().equals(uuid))
               .findFirst()
               .orElseGet(MovieDto::new);
    }

    public void saveHistory(final String searchQuery, User user) {
        User byUsername = userRepository.findByUsername(user.getUsername());
        History history = new History();
        history.setUser(byUsername);
        history.setSearchQuery(searchQuery);
        historyRepository.save(history);
    }
}
