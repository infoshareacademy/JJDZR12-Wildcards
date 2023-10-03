package com.isa.wildcards.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainSearchPageController {
    @GetMapping
    public String getMainSearchPage() {
        return "main-search-page";
    }
}
