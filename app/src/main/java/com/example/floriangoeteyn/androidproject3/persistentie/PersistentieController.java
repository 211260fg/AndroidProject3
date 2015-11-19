package com.example.floriangoeteyn.androidproject3.persistentie;

/**
 * Created by daan on 14/10/2015.
 */

import com.example.floriangoeteyn.androidproject3.domein.Gebruiker;

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
        retrofit = new Retrofit.Builder()
                .baseUrl("https://eva-app-nodejs.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(HerokuService.class);
    }

    public String getFacebookInfo(String req) {
        facebookGraphAPI.accessGraph();
        return facebookGraphAPI.getInfo(req);
    }

    public Call<RetrofitHelper> login(String email, String wachtwoord) throws IOException {
        RetrofitHelper helper = new RetrofitHelper(email, wachtwoord);

        Call<RetrofitHelper> call = service.login(helper);

        return call;
    }

    public Call<RetrofitHelper> registreer(String email, String wachtwoord) throws IOException {
        RetrofitHelper helper = new RetrofitHelper(email, wachtwoord);

        Call<RetrofitHelper> call = service.registreer(helper);

        return call;
    }

}
