package com.PenguinGangT2.Backend.controller;

import java.util.Collection;

import javax.validation.Valid;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.User;
import com.PenguinGangT2.Backend.repository.UserRepository;
// import com.PenguinGangT2.Backend.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class DemoMongoController {

    @Autowired
    private UserRepository userRepo;

    // @Autowired
    // private UserService userService;

    @GetMapping
    public Collection<User> getAll(){
        return userRepo.findAll();
    }
    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable String id){
        // System.out.println(id);
        return userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public User postUser(@RequestBody User user){
        // User user = new User();
        // user.setUsername(username);
        // user.setEmail(email);
        // user.setPassword(password);
        // user.setAccountPoints(0);
        return userRepo.save(user);

    }
    
}
