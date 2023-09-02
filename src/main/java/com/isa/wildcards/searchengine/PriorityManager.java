package com.isa.wildcards.searchengine;

import com.isa.wildcards.entity.Movie;

import java.util.*;

public class PriorityManager {
    public static Map<Movie, Integer> assignPriorityToMovies(List<Movie> movies, Set<String> keywords) {
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

        return movie.getTitle().equalsIgnoreCase(keyword)
                || movie.getYear().equalsIgnoreCase(keyword)
                || movie.getRated().equalsIgnoreCase(keyword)
                || movie.getReleased().equalsIgnoreCase(keyword)
                || movie.getRuntime().equalsIgnoreCase(keyword)
                || movie.getGenre().equalsIgnoreCase(keyword)
                || movie.getDirector().equalsIgnoreCase(keyword)
                || movie.getWriter().equalsIgnoreCase(keyword)
                || movie.getActors().equalsIgnoreCase(keyword)
                || movie.getPlot().equalsIgnoreCase(keyword)
                || movie.getLanguage().equalsIgnoreCase(keyword)
                || movie.getCountry().equalsIgnoreCase(keyword)
                || movie.getAwards().equalsIgnoreCase(keyword)
                || movie.getMetascore().equalsIgnoreCase(keyword);
    }

    public static void showMoviesInPriorityOrder(Map<Movie, Integer> moviesWithPriorityPoints) {
        moviesWithPriorityPoints.entrySet().stream()
                .sorted((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()))
                .forEach(entry -> System.out.println(entry.getKey()));
        System.out.println("-----------------------------------------------------------------------------------------");
    }
}
