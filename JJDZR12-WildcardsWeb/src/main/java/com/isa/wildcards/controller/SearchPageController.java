package com.isa.wildcards.controller;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.entity.History;
import com.isa.wildcards.entity.User;
import com.isa.wildcards.sevice.SearchResultService;
import com.isa.wildcards.sevice.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@SessionAttributes({"resultListModel", "historyQueryList"})
public class SearchPageController {

    private final SearchResultService service;
    private final UserService userService;

    @GetMapping("/")
    public String getMainSearchPage(Model model, @ModelAttribute("successMessage") String successMessage) {
        model.addAttribute("successMessage", successMessage);
        return "main-search-page";
    }

    @GetMapping("/offline")
    public String getMainSearchPageOffline(Model model, HttpSession session) {
        model.addAttribute("resultListModel", null);

        if ((session.getAttribute("historyQueryList") == null || session.getAttribute("historyQueryList").equals(new ArrayList<History>()))
        && session.getAttribute("loggedUser") == null) {
            model.addAttribute("historyQueryList", "You need to be logged");
        } else if (session.getAttribute("historyQueryList").equals(new ArrayList<History>())) {
            model.addAttribute("historyQueryList", "History is empty");
        } else {
            model.addAttribute("historyQueryList", session.getAttribute("historyQueryList"));
        }
        return "main-search-page-offline";
    }

    @PostMapping("/offline")
    public String postSearchQuery(@RequestParam String searchQuery, Model model, HttpSession session) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser != null) {
            service.saveHistory(searchQuery, loggedUser);
            session.setAttribute("historyQueryList", userService.findAllByUser(loggedUser));
            model.addAttribute("historyQueryList", session.getAttribute("historyQueryList"));
        }
        List<MovieDto> searchResultDto = service.findMoviesBySearchQuery(searchQuery);
        model.addAttribute("resultListModel", searchResultDto);
        return "search-result-page";
    }

    @GetMapping("details/{uuid}")
    public String showSearchResultDetails(@PathVariable UUID uuid, Model model) {
        List<MovieDto> searchResultDto = (List<MovieDto>) model.getAttribute("resultListModel");
        MovieDto movieDto = service.getSearchSelectedResult(searchResultDto, uuid);
        model.addAttribute("selectedResult", movieDto);
        return "selected-result-page";
    }
}