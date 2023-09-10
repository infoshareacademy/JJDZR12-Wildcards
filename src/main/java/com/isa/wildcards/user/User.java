package com.isa.wildcards.user;

import java.io.File;
import java.io.IOException;

public class User {
    private String username;
    private String password;
    private File searchHistoryFile;

    public User(final int id, final String username, final String password) throws IOException {
        this.username = username;
        this.password = password;
        this.searchHistoryFile = new File("src/main/resources/userhistory/" + username + ".txt");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public File getSearchHistoryFile() {
        return searchHistoryFile;
    }

    public void setSearchHistoryFile(final File searchHistoryFile) {
        this.searchHistoryFile = searchHistoryFile;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
