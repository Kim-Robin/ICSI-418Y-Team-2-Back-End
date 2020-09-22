package com.PenguinGangT2.Backend.repository;

import com.PenguinGangT2.Backend.models.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    
    
}
