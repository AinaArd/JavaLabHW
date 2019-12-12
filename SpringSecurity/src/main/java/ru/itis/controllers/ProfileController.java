package ru.itis.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.transfer.UserDto;

import javax.servlet.http.HttpServletRequest;

import static ru.itis.transfer.UserDto.from;

@Controller
public class ProfileController {

    @GetMapping("/")
    public String getProfilePage(ModelMap model, Authentication authentication, HttpServletRequest request) {
        if (authentication == null) {
            return "redirect:/login";
        }
        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
        UserDto user = from(details.getUser());
        model.addAttribute("user", user);
        return "profile";


    }
}
