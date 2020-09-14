package com.PenguinGangT2.Backend.service;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseInitialize{

    @PostConstruct
    public void Initialize(){
        try{
            FileInputStream serviceAccount =
                new FileInputStream("./serviceAccount.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://penguingangt2-c9244.firebaseio.com")
                .build();

            FirebaseApp.initializeApp(options);
        } catch(Exception e){
            e.printStackTrace();
        }

    }
}