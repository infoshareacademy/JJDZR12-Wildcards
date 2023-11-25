package com.isa.wildcards.utilities;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SessionManager {

    private boolean isLogged = false;
    private String username;

    public boolean isLoggedIn(){
        return isLogged;
    }

    public void logIn(String username){
        this.isLogged = true;
        this.username = username;
    }

    public void logOut(){
        this.isLogged = false;
        this.username = null;
    }

    public String getUsername(){
        return username;
    }
}
