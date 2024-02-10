package com.isa.wildcards.controller;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.entity.History;
import com.isa.wildcards.entity.User;
import com.isa.wildcards.sevice.SearchResultService;
import com.isa.wildcards.sevice.UserService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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

import static org.assertj.core.api.Fail.fail;
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

    @MockBean
    private UserService userService;

    @Test
    public void testGetMainUserPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("main-search-page"));
    }


    @Test
    void testGetMainSearchPageOffline() {

        MockitoAnnotations.initMocks(this);

        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("historyQueryList")).thenReturn(new ArrayList<>());
        when(session.getAttribute("loggedUser")).thenReturn(null);

        String result = searchPageController.getMainSearchPageOffline(model, session);

        verify(model, times(1)).addAttribute(eq("resultListModel"), isNull());

        assertEquals("main-search-page-offline", result);
    }


    @Test
    public void testPostSearchQuery() throws Exception {
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);

        User loggedUser = new User();
        List<History> historyList = new ArrayList<>();

        when(session.getAttribute("loggedUser")).thenReturn(loggedUser);
        when(userService.findAllByUser(loggedUser)).thenReturn(historyList);

        String searchQuery = "testQuery";
        List<MovieDto> searchResultDto = new ArrayList<>();
        when(searchResultService.findMoviesBySearchQuery(searchQuery)).thenReturn(searchResultDto);

        doNothing().when(searchResultService).saveHistory(eq(searchQuery), eq(loggedUser));

        SearchPageController searchPageController = new SearchPageController(searchResultService, userService);

        String result = searchPageController.postSearchQuery(searchQuery, model, session);

        verify(searchResultService, times(1)).saveHistory(eq(searchQuery), eq(loggedUser));
        verify(session, times(1)).setAttribute(eq("historyQueryList"), eq(historyList));
        verify(model, times(1)).addAttribute(eq("historyQueryList"), isNull());
        verify(model, times(1)).addAttribute(eq("resultListModel"), eq(searchResultDto));

        assertEquals("search-result-page", result);
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
