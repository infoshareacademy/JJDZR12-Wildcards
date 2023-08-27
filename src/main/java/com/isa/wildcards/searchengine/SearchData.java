package com.isa.wildcards.searchengine;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SearchData {
    private static String searchQuery = "";
    public static Scanner scan = new Scanner(System.in);

    private static String getSearchQueryFromUser() {
        System.out.println("Enter something about movie what you want to find...");
        searchQuery = scan.nextLine();
        if (searchQuery.isEmpty()) {
            System.out.println("You should enter something");
            getSearchQueryFromUser();
        }
        return searchQuery;
    }


    public static Set<String> getKeyWords() {
        searchQuery = getSearchQueryFromUser();
        String[] keyWordsArray = searchQuery.split("[^\\p{L}0-9']+");
        return new HashSet<>(Arrays.asList(keyWordsArray));
    }

}