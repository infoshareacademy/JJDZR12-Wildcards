package com.isa.wildcards.searchengine;

import com.isa.wildcards.menu.Color;
import com.isa.wildcards.searchhistory.UserHistory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SearchData {
    private static String searchQuery = "";

    private static String getSearchQueryFromUser(Scanner scan, boolean loggedUser) {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println(Color.CYAN.getCode() + "Enter something about movie which you want to find..." + Color.RESET.getCode());
        searchQuery = scan.nextLine();
        if (searchQuery.isEmpty()) {
            System.out.println("You should enter something");
            getSearchQueryFromUser(scan, loggedUser);
        }
        if (loggedUser) {
            UserHistory.write(searchQuery);
            UserHistory.addQueryToQueriesList(searchQuery);
        }
        return searchQuery;
    }


    public static Set<String> getKeyWords(Scanner scan, boolean loggedUser) {
        searchQuery = getSearchQueryFromUser(scan, loggedUser);
        String[] keyWordsArray = searchQuery.split("(?![^\\p{L}0-9']+)(?i)(?!the|a)[^\\p{L}0-9']+");
        return new HashSet<>(Arrays.asList(keyWordsArray));
    }

    public static Set<String> getKeyWords(String searchQuery) {
        String[] keyWordsArray = searchQuery.split("(?![^\\p{L}0-9']+)(?i)(?!the|a)[^\\p{L}0-9']+");
        return new HashSet<>(Arrays.asList(keyWordsArray));
    }

}