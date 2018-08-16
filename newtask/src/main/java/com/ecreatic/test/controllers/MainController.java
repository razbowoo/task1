package com.ecreatic.test.controllers;

import com.ecreatic.test.model.User;
import com.ecreatic.test.services.UserService;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;



@Controller
public class MainController {
    private final UserService userService;
    private final ShaPasswordEncoder shaPasswordEncoder;

    public MainController(UserService userService, ShaPasswordEncoder shaPasswordEncoder) {
        this.userService = userService;
        this.shaPasswordEncoder = shaPasswordEncoder;
    }

    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }


    @RequestMapping(value = "/")
    public String defaultpage() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }


//    @RequestMapping(value = "/forgot-password")
//    public String forgotPassword() {
//        return "forgot-password";
//    }

    @RequestMapping(value = "/home")
    public String home(Model model, HttpServletRequest request) {
        String email = request.getUserPrincipal().getName();
        User user = userService.findBy(email).get();
        model.addAttribute("user", user);
        model.addAttribute("password", shaPasswordEncoder.encodePassword(user.getPassword(), null));
        return "home";
    }


    @RequestMapping(value = "/update")
    public String update(@RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam String email,
                         @RequestParam int id) {
        User user = userService.findById(id).get();
        if (firstName != null) user.setFirstName(firstName);
        if (lastName != null) user.setLastName(lastName);
        if (email != null) user.setEmail(email);
        userService.update(user);
        return "redirect:/home";
    }

}




