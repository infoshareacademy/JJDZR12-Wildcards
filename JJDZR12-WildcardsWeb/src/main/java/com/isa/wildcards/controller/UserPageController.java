package com.isa.wildcards.controller;

import com.isa.wildcards.dto.UserDto;
import com.isa.wildcards.entity.History;
import com.isa.wildcards.entity.User;
import com.isa.wildcards.sevice.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserPageController {

    private final UserService userService;
    private final HttpSession session;

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @ModelAttribute("mySession")
    public HttpSession session() {
        return session;
    }

    @GetMapping("/user")
    public String getMainUserPage() {
        return "user-main-page";
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration-page";
    }

    @GetMapping("/sign-in")
    public String getSignInPage(Model model, @ModelAttribute("successMessage") String successMessage) {
        model.addAttribute("successMessage", successMessage);
        return "sign-in-page";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("newUser") UserDto userDto, Model model, RedirectAttributes redirectAttributes) {
        try {
            userService.createNewUser(userDto);
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful! You can now log in.");
            return "redirect:/sign-in";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", true);
            return "registration-page";
        }
    }

    @PostMapping("/sign-in")
    public String logInUser(@ModelAttribute("user") User user, Model model, RedirectAttributes redirectAttributes) {
        if (userService.logInUser(user)) {
            redirectAttributes.addFlashAttribute("successMessage", "Hello " + user.getUsername());
            session.setAttribute("loggedUser", user);
            session.setAttribute("historyQueryList", userService.findAllByUser(user).stream().map(History::getSearchQuery).toList());
            return "redirect:/online";
        } else {
            model.addAttribute("error", true);
            return "sign-in-page";
        }
    }

    @GetMapping("/sign-out")
    public String logOutUser() {
        session.removeAttribute("loggedUser");
        session.removeAttribute("historyQueryList");
        session.setAttribute("loggedUser", null);
        session.setAttribute("historyQueryList", null);
        return "redirect:/online";
    }

    @GetMapping("/delete-user")
    public String deleteUser() {
        User loggedUser = (User) session.getAttribute("loggedUser");
        userService.deleteUser(loggedUser);
        session.removeAttribute("loggedUser");
        session.removeAttribute("historyQueryList");
        session.setAttribute("loggedUser", null);
        session.setAttribute("historyQueryList", null);
        return "redirect:/online";
    }

    @GetMapping("/easter-egg")
    public String getEasterEgg() {
        return "easter-egg";
    }
}