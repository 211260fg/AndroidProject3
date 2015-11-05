package com.example.floriangoeteyn.androidproject3.domein;

import com.example.floriangoeteyn.androidproject3.persistentie.PersistentieController;

/**
 * Created by daan on 14/10/2015.
 */
public class DomeinController {

    private Gebruiker gebruiker;
    private PersistentieController pc;

    public DomeinController() {
        pc = new PersistentieController();
    }

    public String getFacebookInfo(String req) {
        return pc.getFacebookInfo(req);
    }

}
