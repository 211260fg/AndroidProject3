package com.example.floriangoeteyn.androidproject3.persistentie;

import java.util.Date;

/**
 * Created by Jeroen on 11/12/2015.
 */
public class RetrofitHelper {

    // Deze velden moeten steeds gelijk zijn aan de velden van het model in de backend!
    // (zie daar auth.js en Users.js)
    // Voorlopig is dit username & password, moet zo snel mogelijk veranderen naar email & password
    private String username;
    private String password;

    public RetrofitHelper() {

    }

    public RetrofitHelper(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
