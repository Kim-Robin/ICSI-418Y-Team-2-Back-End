package com.PenguinGangT2.Backend.models;

import java.util.Collection;

import javax.validation.constraints.Size;

import com.google.firebase.database.annotations.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tournaments")
public class Tournaments {
    
    @Id
    private String id;

    @NotNull
    @Size(max = 100)
    private String tournamentName;

    private Collection<String> registeredTeamId;

    public String getId(){
        return id;
    }

    public String getTournamentName(){
        return tournamentName;
    }

    public void setTournametName(String tournamentName){
        this.tournamentName = tournamentName;
    }

    public Collection<String> getRegisteredTeamId(){
        return registeredTeamId;
    }

    public void setRegisteredTeamId(Collection<String> registeredTeamId){
        this.registeredTeamId = registeredTeamId;
    }


}
