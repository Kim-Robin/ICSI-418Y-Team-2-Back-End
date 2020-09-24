package com.PenguinGangT2.Backend.repository;

import com.PenguinGangT2.Backend.models.Tournaments;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TournamentsRepository extends MongoRepository<Tournaments, String>{
    
}
