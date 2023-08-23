package com.isa.wildcards.searchengine;

import com.isa.wildcards.entities.MovieObject;

import java.io.*;
import java.util.*;

import static com.isa.wildcards.entities.JsonReader.readJsonFile;

public class Search {

    private static List<MovieObject> fromJsonIntoMovieObjectsCollection() {

        String filePath = "JJDZR12-Wildcards/src/main/resources/database.json";
        try {
            List<MovieObject> movies = readJsonFile(filePath);
            return movies;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void searchMovie() {
        ArrayList movies = new ArrayList<>(fromJsonIntoMovieObjectsCollection());
        Set<String> keyWords = new HashSet<>(SearchData.getKeyWords());
        Map<MovieObject, Integer> foundMovies = new HashMap<>(PriorityManager.assignPriorityToMovies(movies, keyWords));
        PriorityManager.showMoviesInPriorityOrder(foundMovies);
        if (foundMovies.isEmpty()) {
            System.out.println("Nothing was found");
        }
        searchMovie();
        SearchData.scan.close();
    }


}