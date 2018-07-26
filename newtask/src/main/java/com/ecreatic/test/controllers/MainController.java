package com.ecreatic.test.controllers;

import com.ecreatic.test.model.User;
import com.ecreatic.test.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class MainController {
    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    @RequestMapping(value = "/home")
    public String home(Model model, HttpServletRequest request) {
        String email=request.getUserPrincipal().getName();
        User user = userService.findBy(email).get();
        model.addAttribute("user",user);
        return "home";
    }

}


