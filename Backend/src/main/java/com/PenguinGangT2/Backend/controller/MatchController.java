package com.PenguinGangT2.Backend.controller;

import java.util.Collection;

import com.PenguinGangT2.Backend.models.Match;
import com.PenguinGangT2.Backend.repository.MatchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchRepository matchRepo;

    @GetMapping
    public Collection<Match> getAll(){
        return matchRepo.findAll();
    }

    
}
