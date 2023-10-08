package com.isa.wildcards.controller;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.sevice.SearchResultService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("resultListModel")
public class SearchPageController {
    private final SearchResultService service;

    public SearchPageController(final SearchResultService service) {
        this.service = service;
    }

    @GetMapping
    public String getMainSearchPage() {
        return "main-search-page";
    }

    @PostMapping
    public String postSearchQuery(@RequestParam String searchQuery, Model model) {
        List<MovieDto> searchResultDto = service.findMoviesBySearchQuery(searchQuery);
        model.addAttribute("resultListModel", searchResultDto);
        return "search-result-page";
    }

    @GetMapping("details/{uuid}")
    public String showSearchResultDetails(@PathVariable String uuid, Model model) {
        List<MovieDto> searchResultDto = (List<MovieDto>) model.getAttribute("resultListModel");
        model.addAttribute("selectedResult", searchResultDto.stream().filter(e -> e.getUuid().equals(uuid)).findFirst().get());
        return "selected-result-page";
    }
}
