package com.PenguinGangT2.Backend.controller;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.Announcement;
import com.PenguinGangT2.Backend.models.Team;
import com.PenguinGangT2.Backend.repository.AnnouncementRepository;
import com.PenguinGangT2.Backend.repository.MatchRepository;
import com.PenguinGangT2.Backend.repository.TeamRepository;
import com.PenguinGangT2.Backend.repository.TournamentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/global")
@CrossOrigin(origins = "*")
public class GlobalController {

    @Autowired
    private AnnouncementRepository announcementRepo;
    @Autowired
    private MatchRepository matchRepo;
    @Autowired
    private TeamRepository teamRepo;
    @Autowired
    private TournamentsRepository tournamentRepo;

    @PostMapping (value = "/action/createTeam")
    public ResponseEntity<?> createTeam(@RequestBody Team team, @RequestParam(name = "userId") String userId, @RequestParam(name = "tournamentId") String tournamentId){
        Map responseMap = new HashMap();
        Map responseUser = new HashMap();
        Map responseTournament = new HashMap();

        return ResponseEntity.ok().body(responseMap);
    }
}
