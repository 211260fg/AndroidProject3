package com.example.floriangoeteyn.androidproject3.domein;

import java.sql.Date;

/**
 * Created by Jeroen on 4/11/2015.
 */
public class Gebruiker {

    private String naam;
    private Date geboorteDatum;
    private String hash;
    private String salt;
    private String geslacht;
    private String leefSituatie;
    private int gezinsleden;
    private int ervaring;
    private String email;
    private Date dagGestart;
    private String facebookId;

    public Gebruiker() {

    }

    public Date getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(Date geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public String getLeefSituatie() {
        return leefSituatie;
    }

    public void setLeefSituatie(String leefSituatie) {
        this.leefSituatie = leefSituatie;
    }

    public int getGezinsleden() {
        return gezinsleden;
    }

    public void setGezinsleden(int gezinsleden) {
        this.gezinsleden = gezinsleden;
    }

    public int getErvaring() {
        return ervaring;
    }

    public void setErvaring(int ervaring) {
        this.ervaring = ervaring;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDagGestart() {
        return dagGestart;
    }

    public void setDagGestart(Date dagGestart) {
        this.dagGestart = dagGestart;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }


}
