package com.ecreatic.test.controllers;

import com.ecreatic.test.model.User;
import com.ecreatic.test.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping
    public String createNewStudent(@Valid User user, BindingResult bindingResult, Model model) {
        String email = user.getEmail();
        Optional<User> existStudent = userService.findBy(email);
        if (existStudent.isPresent()) {
            bindingResult.rejectValue("email", "error.student",
                    "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            userService.save(user);
            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("student", new User());
        }
        return "registration";
    }
}