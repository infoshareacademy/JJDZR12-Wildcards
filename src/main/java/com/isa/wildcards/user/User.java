package com.isa.wildcards.user;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.Objects;
import java.util.UUID;

public class User {

    private UUID uuid;
    private String username;
    private String password;
    private transient LinkedList<String> searchHistory;

    public User(UUID uuid, String username, String password, LinkedList<String> searchHistory) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.searchHistory = searchHistory;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
        return Objects.equals(uuid, user.uuid) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(searchHistory, user.searchHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, username, password, searchHistory);
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", searchHistory=" + searchHistory +
                '}';
    }
}
