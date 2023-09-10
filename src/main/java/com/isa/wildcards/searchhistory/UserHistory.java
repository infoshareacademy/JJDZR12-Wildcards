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

    private UserHistory(final User user, final BufferedWriter bufferedWriter) {
    }

    public static void write(final String searchQuery) throws IOException {
        user.getSearchHistoryFile().createNewFile();
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

    public static void show(Scanner scan) throws IOException {
        if (queriesList.isEmpty()) {
            System.out.println("History is empty");
        } else {
            final AtomicInteger indexOfElementPlusOne = new AtomicInteger();
            List<String> copyList = new ArrayList<>(queriesList);
            Collections.reverse(copyList);
            System.out.println(Color.MAGENTA.getCode() + "Enter order number of search query" + Color.RESET.getCode());
            copyList.forEach(s -> System.out.println(indexOfElementPlusOne.addAndGet(1) + ": " + s));
            System.out.println(Color.MAGENTA.getCode() + "Or \"0\" to return to previous menu" + Color.RESET.getCode());
            getUserChoice(scan, indexOfElementPlusOne);
        }
    }

    private static void getUserChoice(Scanner scan, AtomicInteger rangeOfChecking) throws IOException {
        while (true) {
            if (!scan.hasNextInt()) {
                MenuUtils.wrongInput();
                scan.nextLine();
                continue;
            }
            int choice = scan.nextInt();
            scan.nextLine();

            if (choice > 0 && choice <= rangeOfChecking.get()) {
                Search.searchMovie(scan, true, queriesList.get(choice - 1));
                break;
            } else if (choice == 0) {
                break;
            } else {
                MenuUtils.invalidChoice();
            }
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

    private static List<String> getExistQueries() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(user.getSearchHistoryFile()));
        List<String> list = new LinkedList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        return list;
    }

    public static void inicializeQueriesList() throws FileNotFoundException {
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
