package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.repositories.UsersRepository;

@Controller
@RequestMapping(path = "/users")
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping
    public String getUsers(ModelMap model) {
        model.addAttribute("users", usersRepository.findAll());
        return "users";
    }

   /* @RequestMapping(path = "/users", method = RequestMethod.GET, params = {"userName"})
    public String getUserByName(@RequestParam("userName") String name, ModelMap model) {
        List<User> usersCandidates = usersRepository.findByName(name);
        model.addAttribute("users", usersCandidates);
        return "users";
    }*/

    @RequestMapping(method = RequestMethod.POST)
    public String sendToRegister(){
        return "redirect:register";
    }

    /*@ResponseBody
    @RequestMapping("/string")
    public String showString(){
        return "Aina";
    }*/

}
