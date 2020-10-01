package com.PenguinGangT2.Backend.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.User;
import com.PenguinGangT2.Backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserRepository userRepo;

    @PostMapping("/signin")
    public String login(@RequestParam( name = "email") String email, @RequestParam(name = "password") String password){
        System.out.println(userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException()));
        User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        // StandardPasswordEncoder encoder = new StandardPasswordEncoder("secret");
        // System.out.println(encoder.matches(password, user.getPassword()));
        if(user.getPassword().equals(password)){
            return "Success";
        }else{
            return "False";
        }
        
    }

    @PostMapping("signup")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        if(userRepo.existsByUsername(user.getUsername())){
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
        if(userRepo.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        StandardPasswordEncoder encoder = new StandardPasswordEncoder("secret");
        String result = encoder.encode(user.getPassword());
        //
        user.setPassword(result);
        Collection<String> friends = new ArrayList<>();
        user.setFriends(friends);
        user.setAccountPoints(0);

        userRepo.save(user);

        return ResponseEntity.ok("User registered! Welcome");
    }
}
