package com.PenguinGangT2.Backend.controller;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.Team;
import com.PenguinGangT2.Backend.repository.TeamRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
@CrossOrigin(origins = "*")
public class TeamController {

  @Autowired
  private TeamRepository teamRepo;

  @GetMapping
  public Collection<Team> getAll() {
    return teamRepo.findAll();
  }

  @GetMapping("/getTeam")
  public Team getTeamByUserId(
    @Valid @RequestParam(name = "id") String id
  ) {
    return teamRepo
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException());
  }

  @GetMapping("/getTeam/tournament")
  public Collection<Team> getAllTeamsByTourId(@Valid @RequestParam(name="tournamentId") String tourid) {
    List<Team> allTeams = new ArrayList();
    List<Team> returnTeams = new ArrayList();

    for(int i = 0; i < allTeams.size(); i++) {
      if (allTeams.get(i).getTourId() != null && allTeams.get(i).getTourId().equals(tourid)) {
        returnTeams.add(allTeams.get(i));
      }
    }
  return returnTeams;
  }

  @PostMapping("/createTeam")
  public Team createTeam(@RequestBody Team team) {
    return teamRepo.save(team);
  }

  @PutMapping(value = "/{id}")
  public Team updateTeam(@RequestBody Team team, @PathVariable String id) {
    Optional<Team> original = teamRepo.findById(id);

    if (!original.isPresent()) {
      return teamRepo
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException());
    }
    System.out.println(original);

    team.setId(id);

    return teamRepo.save(team);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteTeam(@PathVariable String id) {
    teamRepo.deleteById(id);
  }
}
