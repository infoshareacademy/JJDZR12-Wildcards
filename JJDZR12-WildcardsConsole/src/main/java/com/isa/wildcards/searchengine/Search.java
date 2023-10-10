package com.isa.wildcards.searchengine;

import com.isa.wildcards.entity.Movie;
import com.isa.wildcards.menu.Color;

import java.io.*;
import java.util.*;

import static com.isa.wildcards.utility.JsonReader.readJsonFile;

public class Search {

    private static List<Movie> fromJsonIntoMovieObjectsCollection() {

        String filePath = "JJDZR12-WildcardsConsole/src/main/resources/database.json";
        try {
            return readJsonFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static void searchMovie(Scanner scan, boolean loggedUser) {
        List<Movie> movies = fromJsonIntoMovieObjectsCollection();
        Set<String> keyWords = SearchData.getKeyWords(scan, loggedUser);
        Map<Movie, Integer> foundMovies = PriorityManager.assignPriorityToMovies(movies, keyWords);
        PriorityManager.showMoviesInPriorityOrder(foundMovies);
        if (foundMovies.isEmpty()) {
            System.out.println(Color.YELLOW.getCode() + "Nothing was found" + Color.RESET.getCode());
        }
    }

    public static void searchMovie(String searchQuery) {
        List<Movie> movies = fromJsonIntoMovieObjectsCollection();
        Set<String> keyWords = SearchData.getKeyWords(searchQuery);
        Map<Movie, Integer> foundMovies = PriorityManager.assignPriorityToMovies(movies, keyWords);
        PriorityManager.showMoviesInPriorityOrder(foundMovies);
        if (foundMovies.isEmpty()) {
            System.out.println(Color.YELLOW.getCode() + "Nothing was found" + Color.RESET.getCode());
        }
    }
}


