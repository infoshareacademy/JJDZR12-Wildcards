package com.isa.wildcards.user;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

public class User {
    private transient final UUID id;
    private String username;
    private String password;
    private transient File searchHistoryFile;

    public User(final String username, final String password) {
        this.id = UUID.randomUUID();
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
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        final User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getSearchHistoryFile(), user.getSearchHistoryFile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getUsername(), getPassword(), getSearchHistoryFile());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
