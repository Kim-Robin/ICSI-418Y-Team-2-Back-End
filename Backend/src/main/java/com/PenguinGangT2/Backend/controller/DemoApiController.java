package com.PenguinGangT2.Backend.controller;

import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

@RestController
@RequestMapping("/api")
public class DemoApiController {

    OkHttpClient client = new OkHttpClient();

    @GetMapping("/getApi")
    public String getExample(@RequestBody String name){

        JSONObject obj = new JSONObject(name);
        

        String lastName = obj.getString("lastName");

        Request request = new Request.Builder()
	        .url("https://api-nba-v1.p.rapidapi.com/players/lastName/"+ lastName)
	        .get()
	        .addHeader("x-rapidapi-host", "api-nba-v1.p.rapidapi.com")
	        .addHeader("x-rapidapi-key", "909e58b6e2msheb6f99c22fd8ac6p1235a7jsn4e3b46faea09")
            .build();
            
            Response response = null;
        try{
            response = client.newCall(request).execute();
            System.out.println(name);
            // System.out.println(response);
            return response.body().string();
        }catch(IllegalStateException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("null tester");
        return null;
        
    }
}
