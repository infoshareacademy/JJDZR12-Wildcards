package com.isa.wildcards.sevice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.dto.SearchResultDto;
import com.isa.wildcards.entity.History;
import com.isa.wildcards.entity.Movie;
import com.isa.wildcards.entity.User;
import com.isa.wildcards.repository.HistoryRepository;
import com.isa.wildcards.repository.MovieRepository;
import com.isa.wildcards.repository.UserRepository;
import com.isa.wildcards.utilities.SearchEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@RequiredArgsConstructor
@Service
@Slf4j
public class SearchResultService {

    private final MovieRepository movieRepository;
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final UserService userService;

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
        return historyRepository.saveAndFlush(history);
    }

    /***
     * never returns null
     * @return List<String>
     */
    public List<String> getHistoryIfUserIsLogged(User user) {
        if (user == null || user.equals(new User())) {
            return Collections.singletonList("You need to be logged");
        } else {
            return uploadUserHistoryAndGet(user);
        }
    }

    public List<String> saveHistoryAndUploadUserHistory(final String searchQuery, final User loggedUser) {
        History savedHistory = saveHistory(searchQuery, loggedUser);
        return getHistoryToShow(userService.findAllByUser(loggedUser));
    }

    private List<String> uploadUserHistoryAndGet(final User loggedUser) {
        return getHistoryToShow(userService.findAllByUser(loggedUser));
    }

    private List<String> getHistoryToShow(List<History> history) {
        List<String> stringUserHistory = new ArrayList<>(
                history.stream()
                        .map(History::getSearchQuery)
                        .toList()
        );
        if (stringUserHistory.isEmpty()) {
            stringUserHistory.add("History is empty");
        }
        return stringUserHistory;
    }

    public List<SearchResultDto> getSearchResultDtoList(final String searchQuery) {

        List<SearchResultDto> resultList = new ArrayList<>(10);
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("https://www.googleapis.com/customsearch/v1?key=AIzaSyD7-peB3tpupoV416qTP21Yr0HSEkvjS6o&cx=a27682a4a99da43bf&q=" + searchQuery);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            String jsonResult = EntityUtils.toString(entity);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResult);
            JsonNode itemsNode = rootNode.get("items");

            for (int i = 0; i < 10; i++) {

                String title = itemsNode.get(+i).get("title").asText();
                String link = itemsNode.get(i).get("link").asText();
                String displayLink = itemsNode.get(i).get("displayLink").asText();

                resultList.add(new SearchResultDto(title, link, displayLink));
            }

        } catch (Exception e) {
            log.error("Unsuccessful request in getSearchResultDtoList method");
            return Collections.singletonList(new SearchResultDto("Unsuccessful request, please use offline mode",
                    "http://localhost:8080/offline",
                    "/offline"));
        }
        return resultList;
    }
}