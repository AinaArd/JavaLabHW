package ru.itis.longpolling.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.longpolling.forms.SignUpForm;
import ru.itis.longpolling.services.UserService;


@RestController
public class SignUpController {

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    @PreAuthorize("permitAll()")
    public void signUpUser(@RequestBody SignUpForm signUpForm) {
        userService.signUp(signUpForm);
    }
}
