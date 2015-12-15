package com.example.floriangoeteyn.androidproject3.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daan on 3/11/2015.
 */
public class Recipe implements Challenge, Serializable{
    private String title;
    private String tagline;
    private String quote;
    private String desc;
    private String recipe_type;
    private String skill;
    private String time;
    private List<Ingredient> ingredients= new ArrayList<>();
    private List<Image> images = new ArrayList<>();


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Image> getImages() {
        return images;
    }

    public String getRecipe_type() {
        return recipe_type;
    }

    public void setRecipe_type(String recipe_type) {
        this.recipe_type = recipe_type;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
    public String toString(){return title+"\n"+ingredients+"\n\n\n";}

}
