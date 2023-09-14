package com.isa.wildcards.searchhistory;

import com.isa.wildcards.menu.Color;
import com.isa.wildcards.menu.MenuUtils;
import com.isa.wildcards.searchengine.Search;
import com.isa.wildcards.user.User;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UserHistory {
    private static User user;
    private static RandomAccessFile randomAccessFile;
    private static List<String> queriesList;

    public static void write(final String searchQuery) {
        try {
            user.getSearchHistoryFile().createNewFile();
        } catch (IOException e) {
            System.out.println("Error: failed to create file");
        }
        try {
            randomAccessFile.seek(randomAccessFile.length());
            if (randomAccessFile.length() == 0) {
                randomAccessFile.writeBytes(searchQuery);
            } else {
                randomAccessFile.writeBytes("\n" + searchQuery);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void show(Scanner scan) {
        if (queriesList.isEmpty()) {
            System.out.println("History is empty");
        } else {
            final AtomicInteger indexOfElementPlusOne = new AtomicInteger();
            List<String> copyList = new ArrayList<>(queriesList);
            Collections.reverse(copyList);
            System.out.println(Color.MAGENTA.getCode() + "Enter order number of search query" + Color.RESET.getCode());
            copyList.forEach(searchOption -> System.out.println(indexOfElementPlusOne.addAndGet(1) + ": " + searchOption));
            System.out.println(Color.MAGENTA.getCode() + "Or \"0\" to return to previous menu" + Color.RESET.getCode());
            getUserChoice(scan, indexOfElementPlusOne, copyList);
        }
    }

    private static void getUserChoice(Scanner scan, AtomicInteger rangeOfChecking, List<String> reversedList) {

        if (!scan.hasNextInt()) {
            MenuUtils.wrongInput();
            scan.nextLine();
            getUserChoice(scan, rangeOfChecking, reversedList);
        }
        int choice = scan.nextInt();
        scan.nextLine();

        if (choice > 0 && choice <= rangeOfChecking.get()) {
            Search.searchMovie(reversedList.get(choice - 1));
        } else if (choice < 0) {
            MenuUtils.invalidChoice();
        }
    }


    public static void setValuesUserHistoryWriter(final User user) {
        UserHistory.user = user;
        try {
            randomAccessFile = new RandomAccessFile(user.getSearchHistoryFile(), "rw");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> getExistQueries() {
        List<String> list = new LinkedList<>();
        try (Scanner scanner = new Scanner(new FileReader(user.getSearchHistoryFile()))) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        }
        return list;
    }

    public static void initializeQueriesList() {
        if (queriesList == null) {
            queriesList = new LinkedList<>(getExistQueries());
        }
    }

    public static User getUser() {
        return user;
    }

    public static RandomAccessFile getRandomAccessFile() {
        return randomAccessFile;
    }

    public static void addQueryToQueriesList(String query) {
        queriesList.add(query);
    }
}
