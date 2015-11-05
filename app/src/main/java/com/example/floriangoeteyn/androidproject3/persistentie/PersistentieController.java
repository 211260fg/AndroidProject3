package com.example.floriangoeteyn.androidproject3.persistentie;

/**
 * Created by daan on 14/10/2015.
 */
public class PersistentieController {
    private FacebookGraphAPI facebookGraphAPI;

    public PersistentieController() {
        facebookGraphAPI = new FacebookGraphAPI();
    }

    public String getFacebookInfo(String req) {
        facebookGraphAPI.accessGraph();
        return facebookGraphAPI.getInfo(req);
    }

}
