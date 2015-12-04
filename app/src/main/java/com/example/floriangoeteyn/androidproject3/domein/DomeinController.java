package com.example.floriangoeteyn.androidproject3.domein;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.floriangoeteyn.androidproject3.persistentie.PersistentieController;
import com.example.floriangoeteyn.androidproject3.persistentie.RetrofitHelper;

import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

import retrofit.Call;

/**
 * Created by daan on 14/10/2015.
 */
public class DomeinController {

    private Gebruiker gebruiker;
    private PersistentieController pc;

    private static DomeinController dc;

    private DomeinController() {
        pc = new PersistentieController();
    }

    public static DomeinController getInstance() {
        if (dc == null) {
            dc = new DomeinController();
        }
        return dc;
    }

    public Call<JSONObject> test() {return pc.test(gebruiker.getToken()); }

    public String getFacebookInfo(String req) {
        return pc.getFacebookInfo(req);
    }

    public Call<Gebruiker> login(String email, String password) throws IOException {
        gebruiker = new Gebruiker(email, password);
        return pc.login(gebruiker);
    }

    public Call<Gebruiker> registreer(String email, String password) throws IOException {
        gebruiker = new Gebruiker(email, password);
        return pc.registreer(gebruiker);
    }

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    public PersistentieController getPc() {
        return pc;
    }

    public void setPc(PersistentieController pc) {
        this.pc = pc;
    }

    public String getToken() { return gebruiker.getToken(); }

    public void setToken(String token) { gebruiker.setToken(token); }
}
