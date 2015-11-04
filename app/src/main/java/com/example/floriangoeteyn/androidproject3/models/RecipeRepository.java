package com.example.floriangoeteyn.androidproject3.models;

import com.example.floriangoeteyn.androidproject3.rest.RestClient;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by floriangoeteyn on 04-Nov-15.
 */
public class RecipeRepository {
    private List<Recipe> recipes;


    public List<Recipe> getRecipesFromServer(){

        RestClient.RecipeApiInterface service= RestClient.getClient();
        Call<List<Recipe>> call=service.getRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Response<List<Recipe>> response){
                List<Recipe> result = response.body();
                if(response.isSuccess()){
                    recipes = result;
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        return recipes;
    }
}
