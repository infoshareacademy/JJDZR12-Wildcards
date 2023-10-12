package com.isa.wildcards.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPageController {
    @GetMapping("/user")
    public String getMainUserPage() {
        return "user-main-page";
    }
}

