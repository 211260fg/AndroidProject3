package com.example.floriangoeteyn.androidproject3.persistentie;

/**
 * Created by daan on 14/10/2015.
 */

import com.example.floriangoeteyn.androidproject3.domein.Gebruiker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class PersistentieController implements Serializable {
    private FacebookGraphAPI facebookGraphAPI;
    private Retrofit retrofit;
    private HerokuService service;

    public PersistentieController() {
        facebookGraphAPI = new FacebookGraphAPI();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://eva-app-nodejs.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(HerokuService.class);
    }

    public Call<JSONObject> test(String token) {
        Call<JSONObject> call = service.test("Bearer " + token);

        return call;
    }

    public String getFacebookInfo(String req) {
        facebookGraphAPI.accessGraph();
        return facebookGraphAPI.getInfo(req);
    }

    public Call<Gebruiker> login(Gebruiker gebruiker) throws IOException {

        Call<Gebruiker> call = service.login(gebruiker);

        return call;
    }

    public Call<Gebruiker> registreer(Gebruiker gebruiker) throws IOException {
        Call<Gebruiker> call = service.registreer(gebruiker);

        return call;
    }

    public Call<Gebruiker> getGebruiker(Gebruiker gebruiker) throws IOException {
        Call<Gebruiker> call = service.getGebruiker(gebruiker);

        return call;
    }

}
