package com.dan.ticketing.controller;

import com.dan.ticketing.models.Greeting;
import com.dan.ticketing.models.User;
import com.dan.ticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        model.addAttribute("greeting", greeting);
        return "result";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/addUser")
    public String addUser(Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("availableRoles", Arrays.asList("ROLE_ADMIN","ROLE_OPERATOR","ROLE_RESOLVER"));

        return "addUser";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addUser")
    public String postAddUser(@ModelAttribute("user") User user, Model model) {
        userService.save(user);
        return "redirect:/addUser";
    }

    @GetMapping("/viewUsers")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "viewUsers";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") String id){
        userService.delete(Integer.valueOf(id));
        return "redirect:/viewUsers";
    }
}
