package com.PenguinGangT2.Backend.repository;

import com.PenguinGangT2.Backend.models.Match;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepository extends MongoRepository<Match, String>{
    
}
