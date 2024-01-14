package com.isa.wildcards.controller;

import com.isa.wildcards.sevice.SearchResultService;
import com.isa.wildcards.sevice.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@WebMvcTest(SearchPageController.class)
public class SearchPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchResultService searchResultService;

    @MockBean
    private UserService userService;

    @Test
    public void testGetMainUserPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("main-search-page"));
    }
}
