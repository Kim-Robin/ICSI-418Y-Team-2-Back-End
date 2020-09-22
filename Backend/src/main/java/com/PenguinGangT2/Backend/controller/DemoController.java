package com.PenguinGangT2.Backend.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

import com.PenguinGangT2.Backend.models.Person;
import com.PenguinGangT2.Backend.service.FirebaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
// @RequestMapping("/one")
public class DemoController {

    @Autowired
    FirebaseService fireBaseService;
   
    @GetMapping("/getUserDetails")
    public Person getExample(@RequestHeader String name) throws InterruptedException, ExecutionException{
        return fireBaseService.getUserDetails(name);
    }

    @PostMapping("/createUser")
    public String postExample(@RequestBody Person person) throws InterruptedException, ExecutionException{
        return fireBaseService.saveUserDetails(person); 
    }

    @PutMapping("/updateUser")
    public String putExample(@RequestBody Person person) throws InterruptedException, ExecutionException{
        return fireBaseService.updateUserDetails(person);
    }

    @DeleteMapping("/deleteUser")
    public String deleteExample(@RequestHeader String name){
        return fireBaseService.deleteUser(name);
    }
}
