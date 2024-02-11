package com.isa.wildcards.utilities;

import com.isa.wildcards.entity.Movie;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@UtilityClass
public class SearchEngine {

    private final String SPLITTER = "(?![^\\p{L}0-9']+)(?i)(?!the|a)[^\\p{L}0-9']+";

    public Map<Movie, Integer> findMovies(List<Movie> movies, String searchQuery) {
        Set<String> keywords = Arrays.stream(searchQuery.split(SPLITTER)).collect(toSet());

        Map<Movie, Integer> moviePriorityMap = new HashMap<>();

        for (Movie movie : movies) {
            int priority = calculatePriority(movie, keywords);
            if (priority > 0) {
                moviePriorityMap.put(movie, priority);
            }
        }

        return moviePriorityMap;
    }

    private int calculatePriority(Movie movie, Set<String> keywords) {
        int priority = 0;

        for (String keyword : keywords) {
            if (containsKeyword(movie, keyword)) {
                priority++;
            }
        }

        return priority;
    }

    private boolean containsKeyword(Movie movie, String keyword) {
        return movie.getTitle().toLowerCase().contains(keyword.toLowerCase())
                || movie.getYear().equalsIgnoreCase(keyword)
                || movie.getRated().equalsIgnoreCase(keyword)
                || movie.getReleased().toLowerCase().contains(keyword.toLowerCase())
                || movie.getRuntime().equalsIgnoreCase(keyword)
                || movie.getGenre().toLowerCase().contains(keyword.toLowerCase())
                || movie.getDirector().toLowerCase().contains(keyword.toLowerCase())
                || movie.getWriter().toLowerCase().contains(keyword.toLowerCase())
                || movie.getActors().toLowerCase().contains(keyword.toLowerCase())
                || movie.getPlot().toLowerCase().contains(keyword.toLowerCase())
                || movie.getLanguage().equalsIgnoreCase(keyword)
                || movie.getCountry().equalsIgnoreCase(keyword)
                || movie.getAwards().toLowerCase().contains(keyword.toLowerCase())
                || movie.getMetascore().equalsIgnoreCase(keyword);
    }
}