package com.egor.shop.controllers;

import com.egor.shop.models.Item;
import com.egor.shop.models.Role;
import com.egor.shop.models.User;
import com.egor.shop.repo.ItemRepository;
import com.egor.shop.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.Set;

@Controller
public class AdminController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin")
    public String admin(Principal principal, Model model, Authentication authentication) {
        String s = null;
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            s = authority.toString();
        }
        if(s.equals(Role.ADMIN.toString())) {
            Iterable<User> users = userRepository.findAll();
            model.addAttribute("users", users);
            return "admin";
        }else {
            return "admin-error";
        }
    }

//    @GetMapping("/admin/{id}")
//    public String userReviews (@PathVariable(value = "id") long id,Model model) {
//       User user = userRepository.findById(id).orElse(null);
//        assert user != null;
//        Set<Item> items = user.getItems();
//        for (Item c : items) {
//            System.out.println(c.getId());
//        }
//        model.addAttribute("items",items);
//
//        return "user-items";
//    }
}
