package com.PenguinGangT2.Backend.models;

import java.util.ArrayList;
import java.util.Collection;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {

  @Id
  private String id;

  @NotBlank
  @Size(max = 30)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @NotBlank
  @Size(max = 40)
  private String password;

  @NotBlank
  private int accountPoints;

  private ArrayList<String> friendIDs;

  private ArrayList<String> friendRequestIDs;

  private ArrayList<String> tournamentRequestIDs;

  private ArrayList<String> announcementIDs;

  private String tournament1Id;

  private String tournament2Id;

  private String team1Id;

  private String team2Id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ArrayList<String> getTournamentRequestIDs() { return tournamentRequestIDs; }

  public void setTournamentRequestIDs(ArrayList<String> tournamentRequstIDs) { this.tournamentRequestIDs = tournamentRequstIDs; }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAccountPoint() {
    return accountPoints;
  }

  public void setAccountPoints(int accountPoints) {
    this.accountPoints = accountPoints;
  }

  public ArrayList<String> getFriendIDs() {
    return friendIDs;
  }

  public void setFriendIDs(ArrayList<String> friendIDs) {
    this.friendIDs = friendIDs;
  }

  public ArrayList<String> getFriendRequestIDs() {
    return friendRequestIDs;
  }

  public void setFriendRequestIDs(ArrayList<String> friendRequestIDs) {
    this.friendRequestIDs = friendRequestIDs;
  }

  public ArrayList<String> getAnnouncementIDs() {
    return announcementIDs;
  }

  public void setAnnouncementIDs(ArrayList<String> announcementIDs) {
    this.announcementIDs = announcementIDs;
  }

  public String getTournament1Id() {
    return tournament1Id;
  }

  public void setTournament1Id(String tournament1Id) {
    this.tournament1Id = tournament1Id;
  }

  public String getTournament2Id() {
    return tournament2Id;
  }

  public void setTournament2Id(String tournament2Id) {
    this.tournament2Id = tournament2Id;
  }

  public String getTeam1Id() {
    return team1Id;
  }

  public void setTeam1Id(String team1Id) {
    this.team1Id = team1Id;
  }

  public String getTeam2Id() {
    return team2Id;
  }

  public void setTeam2Id(String team2Id) {
    this.team2Id = team2Id;
  }
}
