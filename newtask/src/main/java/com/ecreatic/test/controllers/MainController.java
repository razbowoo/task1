package com.ecreatic.test.controllers;

import com.ecreatic.test.model.User;
import com.ecreatic.test.services.UserService;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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


    public static class passwordvalidation {
        public static void main(String[] args) {
            String password = "aaZZa44@";
            String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
            System.out.println(password.matches(pattern));
        }
    }


    public static class emailvalidation {
        public static void main(String[] args) {
            int counter = 0;
            String email = "example@gmail.com";
            Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$");
            Matcher matcher = pattern.matcher(email);

            while (matcher.find()) {
                counter++;
                System.out.println("Matches found: " +
                        email.substring(matcher.start(), matcher.end()) +
                        "'. Starting at index" + matcher.start() +
                        "'. and ending at index" + matcher.end());
            }
            System.out.println("Matches found: " + counter);
        }
    }
}


