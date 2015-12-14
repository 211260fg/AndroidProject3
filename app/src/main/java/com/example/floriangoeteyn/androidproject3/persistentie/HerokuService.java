package com.example.floriangoeteyn.androidproject3.persistentie;

import com.example.floriangoeteyn.androidproject3.domein.Gebruiker;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Jeroen on 11/12/2015.
 */
public interface HerokuService {
    @POST("/login")
    Call<Gebruiker> login(@Body Gebruiker gebruiker);

    @POST("/register")
    Call<Gebruiker> registreer(@Body Gebruiker gebruiker);

    @POST("/user/getUser/ByEmail")
    Call<Gebruiker> getGebruiker(@Body Gebruiker gebruiker);

    @GET("/recipes/5645d9a78ed96915e17a39fe")
    Call<JSONObject> test(@Header("Authorization") String auth);
}
