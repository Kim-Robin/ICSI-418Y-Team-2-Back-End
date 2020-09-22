package com.PenguinGangT2.Backend.controller;

import java.util.Collection;

import javax.validation.Valid;

import com.PenguinGangT2.Backend.models.User;
import com.PenguinGangT2.Backend.repository.UserRepository;
// import com.PenguinGangT2.Backend.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class DemoMongoController {

    @Autowired
    private UserRepository userRepo;

    // @Autowired
    // private UserService userService;

    @GetMapping
    public Collection<User> getAll(){
        return userRepo.findAll();
    }
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public User postUser(@Valid @RequestBody User user){
        return userRepo.save(user);

    }
    
}
