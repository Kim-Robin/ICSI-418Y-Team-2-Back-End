package com.PenguinGangT2.Backend.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tournaments")
public class Tournaments {

  @Id
  private String id;

  private String hostUserId;

  private String hostUsername;

  private boolean lockedInStatus;

  private boolean publicStatus;

  private boolean activeStatus;

  private String type;

  private ArrayList<String> completedMatchIds;

  private ArrayList<String> upcomingMatchIds;

  @NotNull
  @Size(max = 100)
  private String tournamentName;

  private ArrayList<String> registeredTeamId;

  private ArrayList<String> registeredUserId;

  @NotNull
  private int participantLimit;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ArrayList<String> getRegisteredUserId() { return registeredUserId; }

  public void setRegisteredUserId(ArrayList<String> registeredUserId) { this.registeredUserId = registeredUserId; }

  public ArrayList<String> getCompletedMatchIds() { return completedMatchIds; }

  public void setCompletedMatchIds(ArrayList<String> completedMatchIds) { this.completedMatchIds = completedMatchIds; }

  public ArrayList<String> getUpcomingMatchIds() { return upcomingMatchIds; }

  public void setUpcomingMatchIds(ArrayList<String> upcomingMatchIds) { this.upcomingMatchIds = upcomingMatchIds; }

  public String getHostUserId() {
    return hostUserId;
  }

  public void setHostUserId(String hostUserId) {
    this.hostUserId = hostUserId;
  }

  public String getHostUsername() {
    return hostUsername;
  }

  public void setHostUsername(String hostUsername) {
    this.hostUsername = hostUsername;
  }

  public boolean getLockedInStatus() {
    return lockedInStatus;
  }

  public void setLockedInStatus(boolean lockedInStatus) {
    this.lockedInStatus = lockedInStatus;
  }

  public boolean getPublicStatus() {
    return publicStatus;
  }

  public void setPublicStatus(boolean publicStatus) {
    this.publicStatus = publicStatus;
  }

  public String getTournamentName() {
    return tournamentName;
  }

  public void setTournametName(String tournamentName) {
    this.tournamentName = tournamentName;
  }

  public ArrayList<String> getRegisteredTeamId() {
    return registeredTeamId;
  }

  public void setRegisteredTeamId(ArrayList<String> registeredTeamId) {
    this.registeredTeamId = registeredTeamId;
  }

  public int getParticipantLimit() {
    return participantLimit;
  }

  public void setParticipantLimit(int participantLimit) {
    this.participantLimit = participantLimit;
  }

  public boolean getActiveStatus() {
    return activeStatus;
  }

  public void setStatus(boolean activeStatus) {
    this.activeStatus = activeStatus;
  }
}
