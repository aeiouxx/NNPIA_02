package com.example.demo.controllers;

import com.example.demo.dao.AppUser;
import com.example.demo.repository.AppUserRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;

import javax.print.attribute.standard.Media;

@RestController
public class AppUserController {
    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping(value = "/app-users", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<AppUser> listAll(Model model) throws Exception {
        List<AppUser> users = appUserRepository.findAll();
        model.addAttribute("users", users);
        return users;
    }

    @GetMapping(value = "/app-users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUser> getUserByid(@PathVariable(value = "id") int id) {
        Optional<AppUser> user = appUserRepository.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/app-users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Validated @RequestBody AppUser newUser) {
        newUser.setCreationDate(new Date());
        newUser.setUpdateDate(new Date());
        var savedUser = appUserRepository.save(newUser);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping(value = "/app-users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUser> updateUser(@PathVariable(value = "id") int id, @RequestBody AppUser userDetails) {
        Optional<AppUser> existingUser = appUserRepository.findById(id);
        if (existingUser.isPresent()) {
            AppUser updatedUser = existingUser.get();
            updatedUser.setUsername(userDetails.getUsername());
            updatedUser.setPassword(userDetails.getPassword());
            updatedUser.setActive(userDetails.getActive());
            updatedUser.setUpdateDate(new Date());
            appUserRepository.save(updatedUser);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/app-users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") int id) {
        return appUserRepository.findById(id)
                .map(user -> {
                    appUserRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}