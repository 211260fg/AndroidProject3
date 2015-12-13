package com.example.floriangoeteyn.androidproject3.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.floriangoeteyn.androidproject3.R;
import com.example.floriangoeteyn.androidproject3.adapter.RecipeAdapter;
import com.example.floriangoeteyn.androidproject3.models.Recipe;
import com.example.floriangoeteyn.androidproject3.repository.RecipeRepository;
import com.example.floriangoeteyn.androidproject3.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class RecipeActivity extends AppCompatActivity implements Callback<List<Recipe>> {

    private RecipeRepository recipeRepository;
    private List<Recipe> recipes;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        recipeRepository = new RecipeRepository();
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_recipe);

        dialog = ProgressDialog.show(this, "", "loading...");

        RestClient.RecipeApiInterface service= RestClient.getRecipeClient();
        Call<List<Recipe>> call=service.getRecipes();
        call.enqueue(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponse(Response<List<Recipe>> response) {
        dialog.dismiss();
        recipes = response.body();

        final RecyclerView rv = (RecyclerView)findViewById(R.id.recipeRecyclerView);
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        rv.setLayoutManager(glm);

        final RecipeAdapter adapter = new RecipeAdapter(recipes, this);
        rv.setAdapter(adapter);


        createDropdownLists();
        SearchView sv = (SearchView)findViewById(R.id.searchingredient);


        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                final List<Recipe> filteredModelList = filter(recipes, text);
                adapter.animateTo(filteredModelList);
                rv.scrollToPosition(0);
                return true;
            }

            private List<Recipe> filter(List<Recipe> recipes, String query) {
                query = query.toLowerCase();
                final List<Recipe> filteredModelList = new ArrayList<>();
                for (Recipe r : recipes) {
                    if (r.getTitle().toLowerCase().contains(query)){
                        filteredModelList.add(r);
                    }
                }
                return filteredModelList;
            }
        });
    }



    @Override
    public void onFailure(Throwable t) {
        dialog.dismiss();
        new AlertDialog.Builder(this)
                .setTitle("Connectie probleem")
                .setMessage("Verbind met het internet om de recepten op te halen")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getBaseContext(),MainActivity.class));
                    }})
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void createDropdownLists(){
        Spinner type = (Spinner)findViewById(R.id.types);
        Spinner kooktijd = (Spinner)findViewById(R.id.kooktijd);
        Spinner moeilijkheid = (Spinner)findViewById(R.id.moeilijkheid);
        Spinner allergie = (Spinner)findViewById(R.id.allergie);
        Spinner regio = (Spinner)findViewById(R.id.regio);

        String[] arraySpinner = new String[] {"Type", "Basisbereiding", "Bijgerecht", "Broodbeleg", "Drank", "Hapje", "Hoofdgerecht", "Nagerecht", "Saus", "Soep", "Voorgerecht"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_layout, arraySpinner);
        type.setAdapter(adapter);

        arraySpinner = new String[] {"Kooktijd", "Lang", "Middel", "Snel"};
        adapter = new ArrayAdapter<>(this, R.layout.spinner_layout, arraySpinner);
        kooktijd.setAdapter(adapter);

        arraySpinner = new String[] {"Moeilijkheid", "Voor keukenPrinc(ess)en", "Voor starterse ", "Voor enthousiasten"};
        adapter = new ArrayAdapter<>(this, R.layout.spinner_layout, arraySpinner);
        moeilijkheid.setAdapter(adapter);

        arraySpinner = new String[] {"Allergie", "Glutenvrij", "Suikervrij"};
        adapter = new ArrayAdapter<>(this, R.layout.spinner_layout, arraySpinner);
        allergie.setAdapter(adapter);

        arraySpinner = new String[] {"Regio", "Afrikaans", "Oosters", "Westers", "Zuid-Amerikaans"};
        adapter = new ArrayAdapter<>(this, R.layout.spinner_layout, arraySpinner);
        regio.setAdapter(adapter);

    }
}