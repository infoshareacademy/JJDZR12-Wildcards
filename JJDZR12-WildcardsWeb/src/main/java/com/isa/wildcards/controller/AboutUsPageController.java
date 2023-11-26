package com.isa.wildcards.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUsPageController {
    @GetMapping("/about")
    public String getMainSearchPage() {
        return "about-us";
    }
}
