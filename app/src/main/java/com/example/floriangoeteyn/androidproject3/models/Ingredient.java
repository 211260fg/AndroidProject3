package com.example.floriangoeteyn.androidproject3.models;

/**
 * Created by daan on 3/11/2015.
 */
public class Ingredient {
    private String ingredient;
    private String value;

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String toString(){return ingredient+"; "+value+" | ";}
}
