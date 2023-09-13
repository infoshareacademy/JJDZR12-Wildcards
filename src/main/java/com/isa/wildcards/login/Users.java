package com.isa.wildcards.login;

import com.isa.wildcards.user.User;
import java.util.List;

public class Users {

    private List<User> users;

    public Users(){

    }

    public Users(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}';
    }
}
