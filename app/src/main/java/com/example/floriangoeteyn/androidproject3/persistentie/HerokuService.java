package com.example.floriangoeteyn.androidproject3.persistentie;

import com.example.floriangoeteyn.androidproject3.domein.Gebruiker;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Jeroen on 11/12/2015.
 */
public interface HerokuService {
    @POST("/login")
    Call<RetrofitHelper> login(@Body RetrofitHelper helper);

    @POST("/register")
    Call<RetrofitHelper> registreer(@Body RetrofitHelper helper);

    @GET("/user/:id")
    Call<Gebruiker> getGebruiker(@Body Gebruiker gebruiker);
}
