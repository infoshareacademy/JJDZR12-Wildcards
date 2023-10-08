package com.isa.wildcards.utilities;

import com.isa.wildcards.entity.Movie;
import lombok.experimental.UtilityClass;

import java.util.*;

import static java.util.stream.Collectors.toSet;

@UtilityClass
public class SearchEngine {

    public static Map<Movie, Integer> findMovies(List<Movie> movies, String searchQuery) {
        Set<String> keywords = Arrays.stream(searchQuery.split("(?![^\\p{L}0-9']+)(?i)(?!the|a)[^\\p{L}0-9']+")).collect(toSet());

        Map<Movie, Integer> moviePriorityMap = new HashMap<>();

        for (Movie movie : movies) {
            int priority = calculatePriority(movie, keywords);
            if (priority > 0) {
                moviePriorityMap.put(movie, priority);
            }
        }

        return moviePriorityMap;
    }

    private static int calculatePriority(Movie movie, Set<String> keywords) {
        int priority = 0;

        for (String keyword : keywords) {
            if (containsKeyword(movie, keyword)) {
                priority++;
            }
        }

        return priority;
    }

    private static boolean containsKeyword(Movie movie, String keyword) {

        return movie.getTitle().toLowerCase().contains(keyword.toLowerCase())
                || movie.getYear().equalsIgnoreCase(keyword)
                || movie.getRated().equalsIgnoreCase(keyword)
                || movie.getReleased().toLowerCase().contains(keyword.toLowerCase())
                || movie.getRuntime().equalsIgnoreCase(keyword)
                || movie.getGenre().equalsIgnoreCase(keyword)
                || movie.getDirector().toLowerCase().contains(keyword.toLowerCase())
                || movie.getWriter().toLowerCase().contains(keyword.toLowerCase())
                || movie.getActors().toLowerCase().contains(keyword.toLowerCase())
                || movie.getPlot().toLowerCase().contains(keyword.toLowerCase())
                || movie.getLanguage().equalsIgnoreCase(keyword)
                || movie.getCountry().equalsIgnoreCase(keyword)
                || movie.getAwards().toLowerCase().contains(keyword.toLowerCase())
                || movie.getMetascore().equalsIgnoreCase(keyword);
    }

    public static Set<String> getKeyWords(String searchQuery) {
        String[] keyWordsArray = searchQuery.split("(?![^\\p{L}0-9']+)(?i)(?!the|a)[^\\p{L}0-9']+");
        return new HashSet<>(Arrays.asList(keyWordsArray));
    }

}


