package com.example.floriangoeteyn.androidproject3.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daan on 3/11/2015.
 */
public class Recipe implements Challenge{
    private String title;
    private String tagline;
    private String quote;
    private String desc;
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

    public void setImages(List<Image> images) {
        this.images = images;
    }
    public String toString(){return title+"\n"+ingredients+"\n\n\n";}
}
