package com.PenguinGangT2.Backend.controller;

import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DemoApiController {

    OkHttpClient client = new OkHttpClient();

    @GetMapping("/getApi")
    public String getExample(@RequestBody String name){

        JSONObject obj = new JSONObject(name);
        

        String lastName = obj.getString("lastName");

        Request request = new Request.Builder()
	        .url("https://api-nba-v1.p.rapidapi.com/players/lastName/"+ lastName)
	        .get()
	        .addHeader("x-rapidapi-host", "")
	        .addHeader("x-rapidapi-key", "")
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
