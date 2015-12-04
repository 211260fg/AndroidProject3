package com.example.floriangoeteyn.androidproject3.domein;

import java.sql.Date;

/**
 * Created by Jeroen on 4/11/2015.
 */
public class Gebruiker {

    private String email;
    private String password;
    private String username;
    private String userlang;
    private Date birthyear;
    private String gender;
    private int experience;
    private String leefSituatie;
    private int family;
    private Date dayStarted;
    private String facebookId;
    private String token;

    public Gebruiker() {

    }

    public Gebruiker(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUserlang() {
        return userlang;
    }

    public void setUserlang(String userlang) {
        this.userlang = userlang;
    }

    public Date getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(Date birthyear) {
        this.birthyear = birthyear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getLeefSituatie() {
        return leefSituatie;
    }

    public void setLeefSituatie(String leefSituatie) {
        this.leefSituatie = leefSituatie;
    }

    public int getFamily() {
        return family;
    }

    public void setFamily(int family) {
        this.family = family;
    }

    public Date getDayStarted() {
        return dayStarted;
    }

    public void setDayStarted(Date dayStarted) {
        this.dayStarted = dayStarted;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
