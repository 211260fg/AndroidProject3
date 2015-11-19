package com.example.floriangoeteyn.androidproject3.persistentie;

import java.util.Date;

/**
 * Created by Jeroen on 11/12/2015.
 */
public class RetrofitHelper {

    // Deze velden moeten steeds gelijk zijn aan de velden van het model in de backend!
    // (zie daar auth.js en Users.js)
    // Voorlopig is dit username & password, moet zo snel mogelijk veranderen naar email & password
    private String email;
    private String password;
    private String token;
    private String username = "empty";

    public RetrofitHelper() {

    }

    public RetrofitHelper(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
