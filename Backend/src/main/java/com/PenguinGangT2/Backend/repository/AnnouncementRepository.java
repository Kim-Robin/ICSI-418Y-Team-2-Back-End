package com.PenguinGangT2.Backend.repository;

import java.util.Optional;

import com.PenguinGangT2.Backend.models.Announcement;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnnouncementRepository extends MongoRepository<Announcement, String>{
    Optional<Announcement> findByTitle(String title);
}
