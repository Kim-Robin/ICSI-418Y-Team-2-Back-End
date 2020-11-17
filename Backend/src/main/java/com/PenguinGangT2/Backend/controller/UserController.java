package com.PenguinGangT2.Backend.controller;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.User;
import com.PenguinGangT2.Backend.repository.UserRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userInfo")
@CrossOrigin(origins = "*")
public class UserController {

  @Autowired
  private UserRepository userRepo;

  @GetMapping
  public Collection<User> getAllUsers(){

    return userRepo.findAll();
  }

  @GetMapping(value = "/byId/{id}")
  public User getUserById(@PathVariable String id) {
    return userRepo
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException());
  }

  @GetMapping(value = "/{username}")
  public User getUserByName(@PathVariable String username) {
    // System.out.println(id);
    return userRepo
      .findByUsername(username)
      .orElseThrow(() -> new ResourceNotFoundException());
  }

  @PostMapping(value = "/update/{id}")
  public ResponseEntity<?> updateUserWithId(@RequestBody User user, @PathVariable String id) {
    Map map = new HashMap();


    User original = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
    System.out.println(original);
    user.setPassword(original.getPassword());
    user.setId(id);


    map.put("id", id);
    map.put("username", user.getUsername());
    map.put("email", user.getEmail());
    map.put("firstName", user.getFirstName());
    map.put("lastName", user.getLastName());
    map.put("friendIDs", user.getFriendIDs());
    map.put("announcementIDs", user.getAnnouncementIDs());
    map.put("matchIDs", user.getMatchIDs());
    map.put("tournament1Id", user.getTournament1Id());
    map.put("tournament2Id", user.getTournament2Id());
    map.put("team1Id", user.getTeam1Id());
    map.put("team2Id", user.getTeam2Id());
    map.put("accountPoints", user.getAccountPoint());
    userRepo.save(user);
    return ResponseEntity.ok().body(map);
  }

  @PostMapping(value = "/{id}")
  public ResponseEntity<?> updateUser(
    @RequestBody User user,
    @PathVariable String id
  ) {
    Optional<User> original = userRepo.findById(id);
    Map userMap = new HashMap();
    if (!original.isPresent()) {
      userMap.put("error", "User not found!");
      return ResponseEntity.ok().body(userMap);
    }

    StandardPasswordEncoder encoder = new StandardPasswordEncoder("secret");
    String result = encoder.encode(user.getPassword());
    user.setPassword(result);

    userMap.put("id", user.getId());
    userMap.put("username", user.getUsername());
    userMap.put("email", user.getEmail());
    userMap.put("firstName", user.getFirstName());
    userMap.put("lastName", user.getLastName());
    userMap.put("friendIDs", user.getFriendIDs());
    userMap.put("announcementIDs", user.getAnnouncementIDs());
    userMap.put("matchIDs", user.getMatchIDs());
    userMap.put("tournament1Id", user.getTournament1Id());
    userMap.put("tournament2Id", user.getTournament2Id());
    userMap.put("team1Id", user.getTeam1Id());
    userMap.put("team2Id", user.getTeam2Id());
    userMap.put("accountPoints", user.getAccountPoint());
    userRepo.save(user);
    return ResponseEntity.ok().body(userMap);
  }
}
