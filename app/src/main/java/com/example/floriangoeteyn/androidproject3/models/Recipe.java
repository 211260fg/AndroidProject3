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
    private List<Ingredient> ingredients= new ArrayList<>();
    private List<Image> images = new ArrayList<>();

    public Recipe(String title, String tagline, String quote, String desc, List<Ingredient> ingredients, List<Image> images){
        this.title=title;
        this.tagline=tagline;
        this.quote=quote;
        this.desc=desc;
        this.ingredients=ingredients;
        this.images=images;
    }

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

    public void setImages(List<Image> images) {
        this.images = images;
    }
    public String toString(){return title+"\n"+ingredients+"\n\n\n";}

}
