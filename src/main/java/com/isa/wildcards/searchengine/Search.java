package com.isa.wildcards.searchengine;

import com.isa.wildcards.entity.Movie;

import java.io.*;
import java.util.*;

import static com.isa.wildcards.utility.JsonReader.readJsonFile;

public class Search {

    private static List<Movie> fromJsonIntoMovieObjectsCollection() {

        String filePath = "src/main/resources/database.json";
        try {
            return readJsonFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            List<Movie> movies = null;
            return movies;
        }
    }

    public static void searchMovie(Scanner scan) {
        List<Movie> movies = fromJsonIntoMovieObjectsCollection();
        Set<String> keyWords = SearchData.getKeyWords(scan);
        Map<Movie, Integer> foundMovies = PriorityManager.assignPriorityToMovies(movies, keyWords);
        PriorityManager.showMoviesInPriorityOrder(foundMovies);
        if (foundMovies.isEmpty()) {
            System.out.println("Nothing was found");
        }
    }
}


