package com.example.floriangoeteyn.androidproject3.repository;

import com.example.floriangoeteyn.androidproject3.models.Recipe;
import com.example.floriangoeteyn.androidproject3.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by floriangoeteyn on 04-Nov-15.
 */
public class RecipeRepository implements Callback<List<Recipe>>{
    private List<Recipe> recipes;



    @Override
    public void onResponse(Response<List<Recipe>> response) {
        this.recipes=response.body();
    }

    @Override
    public void onFailure(Throwable t) {
        recipes=new ArrayList<>();
    }

    public List<Recipe> getRecipes(){
        return recipes;
    }
}
