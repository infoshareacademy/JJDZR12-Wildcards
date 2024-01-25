package com.isa.wildcards.sevice;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.entity.History;
import com.isa.wildcards.entity.Movie;
import com.isa.wildcards.entity.User;
import com.isa.wildcards.repository.HistoryRepository;
import com.isa.wildcards.repository.MovieRepository;
import com.isa.wildcards.repository.UserRepository;
import com.isa.wildcards.utilities.SearchEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class SearchResultService {

    private final MovieRepository movieRepository;
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;

    public List<MovieDto> findMoviesBySearchQuery(String searchQuery) {
        log.info("Finding movies by search query: {}", searchQuery);
        List<Movie> movieList = movieRepository.findAll();
        Map<Movie, Integer> foundMovies = SearchEngine.findMovies(movieList, searchQuery);
        log.info("Movies found: {}", foundMovies.size());
        return MoviesSearchResultMapper.toMoviesDto(foundMovies);
    }

    public MovieDto getSearchSelectedResult(List<MovieDto> searchResultDto, UUID uuid) {
        log.info("Getting selected result with UUID: {}", uuid);
        return searchResultDto.stream()
                .filter(e -> e.getUuid().equals(uuid))
                .findFirst()
                .orElseGet(() -> {
                    log.warn("No movie found with UUID: {}", uuid);
                    return new MovieDto();
                });
    }

    public void saveHistory(final String searchQuery, User user) {
        log.info("Saving search history for user: {} with query: {}", user.getUsername(), searchQuery);
        User byUsername = userRepository.findByUsername(user.getUsername());
        History history = new History();
        history.setUser(byUsername);
        history.setSearchQuery(searchQuery);
        historyRepository.save(history);
        log.info("Search history saved successfully.");
    }
}