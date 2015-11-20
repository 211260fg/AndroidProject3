package com.example.floriangoeteyn.androidproject3.models;

import java.util.Date;
import java.util.List;

/**
 * Created by floriangoeteyn on 20-Nov-15.
 */
public class User {

    private String email;
    private String userlang;
    private String username;
    private Date birthyear;
    private String geslacht;
    private String leeftsituatie;
    private int family;
    private int experience;
    private Date dayStarted;
    private String facebookid;
    private List<Challenge> completedChallenges;
    private Date dayCurrentChallenge;
    private Challenge todaysCompletedChallenge;
    private List<Challenge> currentChallenges;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserlang() {
        return userlang;
    }

    public void setUserlang(String userlang) {
        this.userlang = userlang;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(Date birthyear) {
        this.birthyear = birthyear;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public String getLeeftsituatie() {
        return leeftsituatie;
    }

    public void setLeeftsituatie(String leeftsituatie) {
        this.leeftsituatie = leeftsituatie;
    }

    public int getFamily() {
        return family;
    }

    public void setFamily(int family) {
        this.family = family;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Date getDayStarted() {
        return dayStarted;
    }

    public void setDayStarted(Date dayStarted) {
        this.dayStarted = dayStarted;
    }

    public String getFacebookid() {
        return facebookid;
    }

    public void setFacebookid(String facebookid) {
        this.facebookid = facebookid;
    }

    public List<Challenge> getCompletedChallenges() {
        return completedChallenges;
    }

    public void setCompletedChallenges(List<Challenge> completedChallenges) {
        this.completedChallenges = completedChallenges;
    }

    public Date getDayCurrentChallenge() {
        return dayCurrentChallenge;
    }

    public void setDayCurrentChallenge(Date dayCurrentChallenge) {
        this.dayCurrentChallenge = dayCurrentChallenge;
    }

    public Challenge getTodaysCompletedChallenge() {
        return todaysCompletedChallenge;
    }

    public void setTodaysCompletedChallenge(Challenge todaysCompletedChallenge) {
        this.todaysCompletedChallenge = todaysCompletedChallenge;
    }

    public List<Challenge> getCurrentChallenges() {
        return currentChallenges;
    }

    public void setCurrentChallenges(List<Challenge> currentChallenges) {
        this.currentChallenges = currentChallenges;
    }
}
