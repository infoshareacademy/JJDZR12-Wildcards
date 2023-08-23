package com.isa.wildcards.searchengine;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SearchData {
    private static String searchQuery = null;
    public static Scanner scan = new Scanner(System.in);

    private static String getSearchQueryFromUser() {
        System.out.println("Enter something about movie what you need...");
        searchQuery = scan.nextLine();
        if (searchQuery == "") {
            System.out.println("You should enter something");
            getSearchQueryFromUser();
        }
        return searchQuery;
    }


    public static Set<String> getKeyWords() {
        searchQuery = getSearchQueryFromUser();
        Set<String> keyWordsSet = new HashSet<>();
        String[] keyWordsArray = searchQuery.split("[^\\p{L}0-9']+");
        Arrays.stream(keyWordsArray).forEach(element -> keyWordsSet.add(element));
        return keyWordsSet;
    }

}