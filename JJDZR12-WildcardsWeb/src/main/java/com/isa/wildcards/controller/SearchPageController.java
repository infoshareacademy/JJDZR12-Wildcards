package com.isa.wildcards.controller;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.sevice.SearchResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@SessionAttributes("resultListModel")
public class SearchPageController {

    private final SearchResultService service;

    @GetMapping("/")
    public String getMainSearchPage(Model model, @ModelAttribute("successMessage") String successMessage) {
        model.addAttribute("successMessage", successMessage);
        return "main-search-page";
    }

    @GetMapping("/offline")
    public String getMainSearchPageOffline(Model model) {
        model.addAttribute("resultListModel", null);
        return "main-search-page-offline";
    }

    @PostMapping("/offline")
    public String postSearchQuery(@RequestParam String searchQuery, Model model) {
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