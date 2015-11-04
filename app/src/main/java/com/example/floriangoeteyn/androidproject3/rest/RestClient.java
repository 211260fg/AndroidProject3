package com.example.floriangoeteyn.androidproject3.rest;

import com.example.floriangoeteyn.androidproject3.models.Recipe;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;

/**
 * Created by daan on 3/11/2015.
 */
public class RestClient {

    private static RecipeApiInterface recipeApiInterface ;
    private static String baseUrl = "https://eva-app-nodejs.herokuapp.com" ;

    public static RecipeApiInterface getClient() {
        if (recipeApiInterface == null) {

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverter(String.class, new ToStringConverter())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            recipeApiInterface = client.create(RecipeApiInterface.class);
        }
        return recipeApiInterface ;
    }

public interface RecipeApiInterface {


    @GET("/recipes")
    Call<List<Recipe>> getRecipes();


}
}
