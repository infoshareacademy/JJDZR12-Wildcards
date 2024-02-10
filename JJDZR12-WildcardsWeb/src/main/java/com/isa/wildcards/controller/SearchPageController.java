package com.isa.wildcards.controller;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.dto.SearchResultDto;
import com.isa.wildcards.entity.User;
import com.isa.wildcards.sevice.SearchResultService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SearchPageController {

    private final SearchResultService service;
    private final HttpSession session;

    @ModelAttribute("mySession")
    public HttpSession session() {
        return session;
    }

    @GetMapping("/")
    public String getMain() {
        return "redirect:/online";
    }

    @GetMapping("/online")
    public String getMainSearchPageOnline(Model model, @ModelAttribute("successMessage") String successMessage) {
        model.addAttribute("resultListModel", null);
        model.addAttribute("successMessage", successMessage);
        List<String> history = service.getHistoryIfUserIsLogged((User) session.getAttribute("loggedUser"));
        model.addAttribute("historyQueryList", history);
        return "main-search-page-online";
    }

    @PostMapping("/online")
    public String postSearchQueryOnline(@RequestParam String searchQuery, Model model) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        saveHistoryOnLoggedUser(searchQuery, model, loggedUser);

        List<SearchResultDto> resultList = service.getSearchResultDtoList(searchQuery);

        model.addAttribute("resultListModel", resultList);
        return "search-result-page-online";
    }

    @GetMapping("/offline")
    public String getMainSearchPageOffline(Model model) {
        model.addAttribute("resultListModel", null);
        List<String> history = service.getHistoryIfUserIsLogged((User) session.getAttribute("loggedUser"));
        model.addAttribute("historyQueryList", history);
        return "main-search-page-offline";
    }

    @PostMapping("/offline")
    public String postSearchQueryOffline(@RequestParam String searchQuery, Model model) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        saveHistoryOnLoggedUser(searchQuery, model, loggedUser);

        List<MovieDto> searchResultDto = service.findMoviesBySearchQuery(searchQuery);

        model.addAttribute("resultListModel", searchResultDto);
        return "search-result-page-offline";
    }

    @GetMapping("details/{uuid}")
    public String showSearchResultDetails(@PathVariable UUID uuid, Model model) {
        List<MovieDto> searchResultDto = (List<MovieDto>) model.getAttribute("resultListModel");
        MovieDto movieDto = service.getSearchSelectedResult(searchResultDto, uuid);
        model.addAttribute("selectedResult", movieDto);
        return "selected-result-page";
    }

    private void saveHistoryOnLoggedUser(final String searchQuery, final Model model, final User loggedUser) {
        if (loggedUser != null) {
            List<String> history = service.saveHistoryAndUploadUserHistory(searchQuery, loggedUser);
            model.addAttribute("historyQueryList", history);
        }
    }
}