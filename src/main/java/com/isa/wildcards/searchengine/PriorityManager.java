package com.isa.wildcards.searchengine;

import com.isa.wildcards.entity.MovieObject;

import java.util.*;

public class PriorityManager {
    public static Map<MovieObject, Integer> assignPriorityToMovies(List<MovieObject> movies, Set<String> keywords) {
        Map<MovieObject, Integer> moviePriorityMap = new HashMap<>();

        for (MovieObject movie : movies) {
            int priority = calculatePriority(movie, keywords);
            if (priority > 0) {
                moviePriorityMap.put(movie, priority);
            }
        }

        return moviePriorityMap;
    }

    private static int calculatePriority(MovieObject movie, Set<String> keywords) {
        int priority = 0;

        for (String keyword : keywords) {
            if (containsKeyword(movie, keyword)) {
                priority++;
            }
        }

        return priority;
    }

    private static boolean containsKeyword(MovieObject movie, String keyword) {

        return movie.getTitle().equalsIgnoreCase(keyword)
                || movie.getYear().toLowerCase().contains(keyword.toLowerCase())
                || movie.getRated().equalsIgnoreCase(keyword)
                || movie.getReleased().toLowerCase().contains(keyword.toLowerCase())
                || movie.getRuntime().toLowerCase().contains(keyword.toLowerCase())
                || movie.getGenre().equalsIgnoreCase(keyword)
                || movie.getDirector().equalsIgnoreCase(keyword)
                || movie.getWriter().equalsIgnoreCase(keyword)
                || movie.getActors().equalsIgnoreCase(keyword)
                || movie.getPlot().equalsIgnoreCase(keyword)
                || movie.getLanguage().equalsIgnoreCase(keyword)
                || movie.getCountry().equalsIgnoreCase(keyword)
                || movie.getAwards().toLowerCase().contains(keyword.toLowerCase())
                || movie.getMetascore().equalsIgnoreCase(keyword);
    }

    public static void showMoviesInPriorityOrder(Map<MovieObject, Integer> moviesWithPriorityPoints) {
        List<Map.Entry<MovieObject, Integer>> sortedEntries = new ArrayList<>(moviesWithPriorityPoints.entrySet());
        sortedEntries.sort((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()));

        for (Map.Entry<MovieObject, Integer> entry : sortedEntries) {
            MovieObject movie = entry.getKey();
            System.out.println(movie);
        }
    }
}
