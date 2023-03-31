package com.egor.shop.controllers;


import com.egor.shop.models.Item;
import com.egor.shop.models.Role;
import com.egor.shop.models.User;
import com.egor.shop.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login () {
        return "login";
    }

    @GetMapping("/reg")
    public String reg (@RequestParam(value = "error",defaultValue = "",required = false) String error, Model model) {
        if(error.equals("username")){
            model.addAttribute("error","Такой логин пользователя уже занят");
        }

        return "reg";
    }

    @PostMapping("/reg")
    public String addUser(User userForm) {

        if (userRepository.findByUsername(userForm.getUsername()) != null) {
        return "redirect:/reg?error=username";
        }

        User user = new User(userForm.getUsername(), passwordEncoder.encode(userForm.getPassword()),userForm.getEmail(), true, userForm.getRoles());
        System.out.println(user.getRoles());
        userRepository.save(user);
        return "redirect:/login";
    }
    @GetMapping("/user")
    public String user (Principal principal,Model model) {

        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user",user);


        return "user";
    }

    @PostMapping("/user/update")
    public String userUpdate (Principal principal, User userForm) {
        User user = userRepository.findByUsername(principal.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setRoles(userForm.getRoles());
        userRepository.save(user);
        return "redirect:/user";
    }

}
