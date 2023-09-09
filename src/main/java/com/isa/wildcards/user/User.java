package com.isa.wildcards.user;

import java.util.LinkedList;
import java.util.Objects;

public class User {

    private int id;
    private String username;
    private String password;
    private LinkedList<String> searchHistory;

    public User(int id, String username, String password, LinkedList<String> searchHistory) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.searchHistory = searchHistory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LinkedList<String> getSearchHistory() {
        return searchHistory;
    }

    public void setSearchHistory(LinkedList<String> searchHistory) {
        this.searchHistory = searchHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(searchHistory, user.searchHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, searchHistory);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", searchHistory=" + searchHistory +
                '}';
    }
}
