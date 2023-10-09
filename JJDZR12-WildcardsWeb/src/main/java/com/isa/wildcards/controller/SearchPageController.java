package com.isa.wildcards.controller;

import com.isa.wildcards.dto.MovieDto;
import com.isa.wildcards.sevice.SearchResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Controller
@SessionAttributes("resultListModel")
public class SearchPageController {

    private final SearchResultService service;

    @GetMapping("/")
    public String getMainSearchPage() {
        return "main-search-page";
    }

    @PostMapping("/")
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
