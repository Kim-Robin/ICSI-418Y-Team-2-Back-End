package com.PenguinGangT2.Backend.controller;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.*;
import com.PenguinGangT2.Backend.repository.AnnouncementRepository;
import com.PenguinGangT2.Backend.repository.MatchRepository;
import com.PenguinGangT2.Backend.repository.TeamRepository;
import com.PenguinGangT2.Backend.repository.TournamentsRepository;
import com.PenguinGangT2.Backend.repository.UserRepository;
import io.jsonwebtoken.Header;

import java.nio.file.Path;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/global")
@CrossOrigin(origins = "*")
public class GlobalController {

  @Autowired
  private AnnouncementRepository announcementRepo;

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private MatchRepository matchRepo;

  @Autowired
  private TeamRepository teamRepo;

  @Autowired
  private TournamentsRepository tournamentRepo;

  @GetMapping(value = "/action/getMatches/{tournamentId}")
  public ResponseEntity<?> getMatchesByTournamentId(
    @PathVariable String tournamentId
  ) {
    Map returnMap = new HashMap();
    List<Match> listOfMatches = new ArrayList<>();
    Collection<Match> listOfUnplayedMatches = new ArrayList();
    Collection<Match> listOfPlayedMatches = new ArrayList();
    listOfMatches = matchRepo.findAll();

    for (int i = 0; i < listOfMatches.size(); i++) {
      if (listOfMatches.get(i).getTournamentId().equals(tournamentId)) {
        if (listOfMatches.get(i).getIsPlayed() == true) {
          listOfPlayedMatches.add(listOfMatches.get(i));
        } else {
          listOfUnplayedMatches.add(listOfMatches.get(i));
        }
      }
    }

    returnMap.put("upcomingMatches", listOfUnplayedMatches);
    returnMap.put("playedMatches", listOfPlayedMatches);

    return ResponseEntity.ok().body(returnMap);
  }

  @PostMapping(value = "/action/leaveTournament/{tournamentId}/{userId}")
  public ResponseEntity<?> leaveTournament(@PathVariable String tournamentId, @PathVariable String userId) {

    User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException());

    if (user.getTournament1Id().equals(tournamentId)) {
      user.setTournament1Id("none");
      user.setTeam1Id("none");
      userRepo.save(user);
    } else {
      user.setTournament2Id("none");
      user.setTeam2Id("none");
      userRepo.save(user);
    }

    Tournaments tournament = tournamentRepo.findById(tournamentId).orElseThrow(() -> new ResourceNotFoundException());

    if(tournament.getRegisteredUserId().contains(userId)) {
      ArrayList<String> newUserIds = new ArrayList();
      newUserIds.addAll(tournament.getRegisteredUserId());
      int index = newUserIds.indexOf(userId);
      newUserIds.remove(index);
      tournament.setRegisteredUserId(newUserIds);
      tournamentRepo.save(tournament);
    }
    Map map = new HashMap();
    map.put("message", "Left Tournament Successfully!");
    return ResponseEntity.ok().body(map);
  }

  @PostMapping(value = "/action/lockInTournament/{tournamentId}")
  public ResponseEntity<?> lockInTournament(
    @RequestBody Tournaments tournament
  ) {
    Map returnMap = new HashMap();
    String mytournamentId = tournament.getId();
    int numberOfTeams = tournament.getRegisteredTeamId().size();
    List<String> listOfTeamIds1 = new ArrayList<>();
    List<String> listOfTeamIds2 = new ArrayList<>();
    listOfTeamIds1 = tournament.getRegisteredTeamId();
    listOfTeamIds2 = tournament.getRegisteredTeamId();

    List<Team> listOfTeams = new ArrayList();
    listOfTeams = teamRepo.findAll();

    List<Team> allTeams = new ArrayList();
    List<Team> returnTeams = new ArrayList();
    List<User> returnUsers = new ArrayList();

    for (int i = 0; i < listOfTeams.size(); i++) {
      if (
        listOfTeams.get(i).getTourId() != null &&
        listOfTeams.get(i).getTourId().equals(tournament.getId())
      ) {
        returnTeams.add(listOfTeams.get(i));
        returnUsers.add(
          userRepo
            .findById(listOfTeams.get(i).getUserId())
            .orElseThrow(() -> new ResourceNotFoundException())
        );
      }
    }

    List<User> allUsers = new ArrayList<>();
    allUsers = userRepo.findAll();


    for (int i = 0; i < allUsers.size(); i++) {
      if (allUsers.get(i).getTournament1Id().equals(tournament.getId())) {
        if (allUsers.get(i).getTeam1Id().equals("none")) {
          allUsers.get(i).setTournament1Id("none");
        }

      }

      if (allUsers.get(i).getTournament2Id().equals(tournament.getId())) {
        if (allUsers.get(i).getTeam2Id().equals("none")) {
          allUsers.get(i).setTournament2Id("none");
        }
      }
    }

    List<String> done = new ArrayList();
    List<String> doneUsers = new ArrayList();
    // Create Matches
    String saltChars =
      "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
    for (int i = 0; i < numberOfTeams; i++) {
      for (int j = 0; j < numberOfTeams; j++) {
        StringBuilder salt = new StringBuilder();
        Random random = new Random();
        while (salt.length() < 20) {
          int index = (int) (random.nextFloat() * saltChars.length());
          salt.append(saltChars.charAt(index));
        }
        String matchId = salt.toString();
        Collection<String> newList = new ArrayList();
        Collection<String> newUserIdsList = new ArrayList();
        newList.add(listOfTeamIds1.get(i));
        newUserIdsList.add(returnUsers.get(i).getId());
        if (
          !newList.contains(listOfTeamIds2.get(j)) &&
          !done.contains(listOfTeamIds2.get(j)) &&
          !newUserIdsList.contains(returnUsers.get(j).getId()) &&
          !doneUsers.contains(returnUsers.get(j).getId())
        ) {
          newList.add(listOfTeamIds2.get(j));
          newUserIdsList.add(returnUsers.get(j).getId());
        }
        Match newMatch = new Match();
        newMatch.setId(matchId);
        newMatch.setIsPlayed(false);
        newMatch.setTeamId(newList);
        newMatch.setUserId(newUserIdsList);
        newMatch.setTournamentId(tournament.getId());
        matchRepo.save(newMatch);
      }
      done.add(listOfTeamIds1.get(i));
      doneUsers.add(returnUsers.get(i).getId());
    }
    returnMap.put("message", "Locked In Tournament!");
    return ResponseEntity.ok().body(returnMap);
  }

  @GetMapping("/action/getUpdatedInfo")
  public ResponseEntity<?> getUpdatedUserInformation(
    @RequestParam(name = "userId") String userId
  ) {
    Map returnMap = new HashMap();
    Map userMap = new HashMap();
    User myUser = userRepo
      .findById(userId)
      .orElseThrow(() -> new ResourceNotFoundException());
    String tour1Id = myUser.getTournament1Id();
    String tour2Id = myUser.getTournament2Id();
    String team1Id = myUser.getTeam1Id();
    String team2Id = myUser.getTeam2Id();
    Tournaments tour1;
    Tournaments tour2;
    Team team1;
    Team team2;
    userMap.put("id", myUser.getId());
    userMap.put("username", myUser.getUsername());
    userMap.put("password", "hidden");
    userMap.put("email", myUser.getEmail());
    userMap.put("firstName", myUser.getFirstName());
    userMap.put("lastName", myUser.getLastName());
    userMap.put("friendIDs", myUser.getFriendIDs());
    userMap.put("friendRequestIDs", myUser.getFriendRequestIDs());
    userMap.put("announcementIDs", myUser.getAnnouncementIDs());
    userMap.put("tournamentRequestIDs", myUser.getTournamentRequstIDs());
    userMap.put("tournament1Id", myUser.getTournament1Id());
    userMap.put("tournament2Id", myUser.getTournament2Id());
    userMap.put("team1Id", myUser.getTeam1Id());
    userMap.put("team2Id", myUser.getTeam2Id());
    userMap.put("accountPoints", myUser.getAccountPoint());
    returnMap.put("user", userMap);

    if (!tour1Id.equals("none")) {
      tour1 =
        tournamentRepo
          .findById(tour1Id)
          .orElseThrow(() -> new ResourceNotFoundException());
      returnMap.put("tournament1", tour1);
    } else {
      returnMap.put("tournament1", "none");
    }

    if (!tour2Id.equals("none")) {
      tour2 =
        tournamentRepo
          .findById(tour2Id)
          .orElseThrow(() -> new ResourceNotFoundException());
      returnMap.put("tournament2", tour2);
    } else {
      returnMap.put("tournament2", "none");
    }

    if (!team1Id.equals("none")) {
      team1 =
        teamRepo
          .findById(team1Id)
          .orElseThrow(() -> new ResourceNotFoundException());
      returnMap.put("team1", team1);
    } else {
      returnMap.put("team1", "none");
    }

    if (!team2Id.equals("none")) {
      team2 =
        teamRepo
          .findById(team2Id)
          .orElseThrow(() -> new ResourceNotFoundException());
      returnMap.put("team2", team2);
    } else {
      returnMap.put("team2", "none");
    }
    return ResponseEntity.ok().body(returnMap);
  }

  @PostMapping("/action/createTeam")
  public ResponseEntity<?> createTeam(
    @RequestBody Team team,
    @RequestParam(name = "userId") String userId,
    @RequestParam(name = "tournamentId") String tournamentId
  ) {
    Map responseMap = new HashMap();
    try {
      Team newTeam = teamRepo.save(team);
      String newTeamId = team.getId();

      Tournaments tournament = tournamentRepo
        .findById(tournamentId)
        .orElseThrow(() -> new ResourceNotFoundException());
      ArrayList<String> newRegisteredTeamId = new ArrayList();
      newRegisteredTeamId.addAll(tournament.getRegisteredTeamId());
      newRegisteredTeamId.add(newTeamId);
      tournament.setRegisteredTeamId(newRegisteredTeamId);

      User myUser = userRepo
        .findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException());
      if (tournament.getType().equals("tour1")) {
        myUser.setTeam1Id(newTeamId);
      } else {
        myUser.setTeam2Id(newTeamId);
      }
      responseMap.put("message", "Successfully created team!");
    } catch (Exception e) {
      responseMap.put("error", "Could not create team!");
    }

    return ResponseEntity.ok().body(responseMap);
  }
}
