package com.example.floriangoeteyn.androidproject3.persistentie;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Jeroen on 11/12/2015.
 */
public interface HerokuService {
    @POST("/login")
    Call<JSONObject> login(@Body RetrofitHelper helper);

    @POST("/register")
    Call<JSONObject> registreer(@Body RetrofitHelper helper);
}
