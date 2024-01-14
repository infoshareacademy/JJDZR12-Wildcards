package com.isa.wildcards.controller;

import com.isa.wildcards.dto.UserDto;
import com.isa.wildcards.entity.User;
import com.isa.wildcards.sevice.UserService;
import com.isa.wildcards.utilities.SessionManager;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(UserPageController.class)
public class UserPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private SessionManager sessionManager;

    @MockBean
    private Model model;

    @InjectMocks
    private UserPageController userPageController;

    private MockHttpServletRequestBuilder buildPostRequest(String url, User user, MockHttpSession session) {
        return MockMvcRequestBuilders.post(url)
                .session(session)
                .flashAttr("user", user);
    }

    @Test
    public void testGetMainUserPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user-main-page"));
    }

    @Test
    public void testGetRegistrationPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/registration"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration-page"));
    }

    @Test
    public void testGetSignInPage() throws Exception {
        String successMessage = "Test Success Message";

        mockMvc.perform(MockMvcRequestBuilders.get("/sign-in")
                        .flashAttr("successMessage", successMessage))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("sign-in-page"))
                .andExpect(MockMvcResultMatchers.model().attribute("successMessage", successMessage));
    }

    @Test
    public void testAddUserSuccess() throws Exception {
        UserDto userDto = new UserDto();

        mockMvc.perform(MockMvcRequestBuilders.post("/addUser")
                        .flashAttr("newUser", userDto))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/sign-in"))
                .andExpect(MockMvcResultMatchers.flash().attribute("successMessage", "Registration successful! You can now log in."));

        verify(userService).createNewUser(userDto);
    }

    @Test
    public void testAddUserFailure() throws Exception {
        UserDto userDto = new UserDto();

        doThrow(new IllegalArgumentException("Invalid user data")).when(userService).createNewUser(userDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/addUser")
                        .flashAttr("newUser", userDto))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration-page"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("error"));

        verify(userService).createNewUser(userDto);
    }

    @Test
    public void testLogInUserSuccess() throws Exception {
        User user = new User();
        MockHttpSession session = new MockHttpSession();

        when(userService.logInUser(user)).thenReturn(true);
        when(userService.findAllByUser(user)).thenReturn(Collections.emptyList());

        mockMvc.perform(buildPostRequest("/sign-in", user, session))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
                .andExpect(MockMvcResultMatchers.flash().attribute("successMessage", "Hello " + user.getUsername()));


        assertInstanceOf(User.class, session.getAttribute("loggedUser"));
        assertEquals(user.getUsername(), ((User) session.getAttribute("loggedUser")).getUsername());
        assertInstanceOf(List.class, session.getAttribute("historyQueryList"));
        assertTrue(((List<?>) session.getAttribute("historyQueryList")).isEmpty());
    }

    @Test
    public void testLogInUserFailure() throws Exception {
        User user = new User();
        MockHttpSession session = new MockHttpSession();

        when(userService.logInUser(user)).thenReturn(false);

        mockMvc.perform(buildPostRequest("/sign-in", user, session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("sign-in-page"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("error"));


        assertFalse(session.getAttribute("loggedUser") instanceof User);
        assertNull(session.getAttribute("historyQueryList"));
    }

    @Test
    public void testLogOutUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/sign-out"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"));

        verify(sessionManager).logOut();
    }
}

