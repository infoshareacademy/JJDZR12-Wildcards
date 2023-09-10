package com.isa.wildcards.searchengine;

import com.isa.wildcards.entity.Movie;
import com.isa.wildcards.menu.Color;

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

    public static void showMoviesInPriorityOrder(Map<Movie, Integer> moviesWithPriorityPoints) {
        System.out.println("Your search result: " + "\n" + "\n" + "!! Most accurate result are from top to bottom. !!" + "\n");
        moviesWithPriorityPoints.entrySet().stream()
                .sorted((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()))
                .forEach(entry -> System.out.println(entry.getKey()));
        System.out.println(Color.CYAN.getCode() + "End of search" + "\n" +
                "----------------------------------------------------------------------------------" + Color.RESET.getCode() );
    }
}
