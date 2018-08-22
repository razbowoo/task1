package com.ecreatic.test.controllers;

import com.ecreatic.test.model.User;
import com.ecreatic.test.services.MailService;
import com.ecreatic.test.services.UserService;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Controller
public class MainController {
    private final UserService userService;
    private final ShaPasswordEncoder shaPasswordEncoder;
    private final MailService mailService;

    public MainController(UserService userService, ShaPasswordEncoder shaPasswordEncoder, MailService mailService) {
        this.userService = userService;
        this.shaPasswordEncoder = shaPasswordEncoder;
        this.mailService = mailService;
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


    @RequestMapping(value = "/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

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

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public String resetRequest(@RequestParam(value = "email") String email, Model model) {

        Optional<User> optionalUser = userService.findBy(email);
        if (optionalUser.isPresent()){
            mailService.sendMail(email);
            model.addAttribute("message","success");
        }
        else {
            model.addAttribute("message","error");
        }

        return "forgot-password";
    }

    @RequestMapping(value = "/newPassword/{email}")
    public String resetPassword(@PathVariable String email, Model model) {

        Optional<User> optionalUser = userService.findBy(email);
        if (optionalUser.isPresent()){

            model.addAttribute("message","success");
        }
        else {
            model.addAttribute("message","error");
        }


        model.addAttribute("emailId", email);
        return "newPassword";
    }

}












