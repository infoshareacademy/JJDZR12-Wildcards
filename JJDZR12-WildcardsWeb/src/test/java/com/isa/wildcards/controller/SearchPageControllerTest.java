package com.isa.wildcards.controller;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.dto.SearchResultDto;
import com.isa.wildcards.entity.User;
import com.isa.wildcards.sevice.SearchResultService;
import com.isa.wildcards.sevice.UserService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest(SearchPageController.class)
public class SearchPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private SearchPageController searchPageController;

    @MockBean
    private SearchResultService searchResultService;

    @Mock
    private UserService userService;

    @Test
    public void testGetMainUserPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/online"));
    }

    @Test
    public void testPostSearchQueryOnline() {

        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        User loggedUser = new User();
        when(session.getAttribute("loggedUser")).thenReturn(loggedUser);

        String searchQuery = "testQuery";
        List<SearchResultDto> searchResultDtoList = new ArrayList<>();
        when(searchResultService.getSearchResultDtoList(searchQuery)).thenReturn(searchResultDtoList);

        SearchPageController searchPageController = new SearchPageController(searchResultService, session);

        String result = searchPageController.postSearchQueryOnline(searchQuery, model);

        verify(searchResultService, times(1)).getSearchResultDtoList(eq(searchQuery));
        verify(model, times(1)).addAttribute(eq("resultListModel"), eq(searchResultDtoList));
        assertEquals("search-result-page-online", result);
    }

    @Test
    public void testShowSearchResultDetails() throws Exception {

        MovieDto mockMovieDto = new MovieDto();
        mockMovieDto.setUuid(UUID.randomUUID());

        Mockito.when(searchResultService.getSearchSelectedResult(Mockito.anyList(), Mockito.any(UUID.class)))
                .thenReturn(mockMovieDto);

        List<MovieDto> searchResultDtoList = Collections.singletonList(new MovieDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/details/{uuid}", mockMovieDto.getUuid().toString())
                        .flashAttr("resultListModel", searchResultDtoList))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("selected-result-page"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("selectedResult"))
                .andExpect(MockMvcResultMatchers.model().attribute("selectedResult", mockMovieDto))
                .andExpect(MockMvcResultMatchers.model().attribute("resultListModel", searchResultDtoList));
    }
}
