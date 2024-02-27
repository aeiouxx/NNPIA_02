package com.example.demo.controllers;

import com.example.demo.dao.AppUser;
import com.example.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserController {
    @Autowired
    private AppUserRepository appUserRepository;
    @GetMapping("/users")
    public String listAll(Model model) {
        List<AppUser> users = appUserRepository.findAll();
        model.addAttribute("users", users);

        return "users";
    }
}
