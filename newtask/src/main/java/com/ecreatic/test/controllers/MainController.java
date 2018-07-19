package com.ecreatic.test.controllers;

import com.ecreatic.test.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


    @Controller
    public class MainController {
        private final UserService userService;

        public MainController(UserService userService) {
            this.userService = userService;
        }

        @RequestMapping(value = "/index")
        public String index(Model model){
            Object user;
            model.addAttribute("users", userService.findAll());
            return "index";
        }

        @RequestMapping(value = "/login")
        public String login() { return "login";}




    }


