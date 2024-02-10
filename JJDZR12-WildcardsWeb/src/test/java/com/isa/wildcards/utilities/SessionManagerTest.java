package com.isa.wildcards.utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SessionManagerTest {
    private SessionManager sessionManager;

    @BeforeEach
    public void setUp() {
        sessionManager = new SessionManager();
    }

    @Test
    public void testInitialLoginStatus() {
        assertFalse(sessionManager.isLoggedIn());
        assertNull(sessionManager.getUsername());
    }

    @Test
    public void testLogin() {
        String username = "testUser";
        sessionManager.logIn(username);

        assertTrue(sessionManager.isLoggedIn());
        assertEquals(username, sessionManager.getUsername());
    }

    @Test
    public void testLogout() {

        String username = "testUser";
        sessionManager.logIn(username);

        sessionManager.logOut();

        assertFalse(sessionManager.isLoggedIn());
        assertNull(sessionManager.getUsername());
    }

    @Test
    public void testLoginWithDifferentUser() {

        sessionManager.logIn("user1");

        sessionManager.logIn("user2");

        assertTrue(sessionManager.isLoggedIn());
        assertEquals("user2", sessionManager.getUsername());
    }

    @Test
    public void testLogoutWhenNotLoggedIn() {

        sessionManager.logOut();

        assertFalse(sessionManager.isLoggedIn());
        assertNull(sessionManager.getUsername());
    }
}
