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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.floriangoeteyn.androidproject3.R;
import com.example.floriangoeteyn.androidproject3.adapter.RecipeAdapter;
import com.example.floriangoeteyn.androidproject3.adapter.RestaurantAdapter;
import com.example.floriangoeteyn.androidproject3.models.Recipe;
import com.example.floriangoeteyn.androidproject3.models.Restaurant;
import com.example.floriangoeteyn.androidproject3.rest.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class RestaurantActivity extends AppCompatActivity implements Callback<List<Restaurant>> {

    private List<Restaurant> restaurants;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        dialog = ProgressDialog.show(this, "", "loading...");

        RestClient.RestaurantApiInterface service = RestClient.getRestaurantClient();
        Call<List<Restaurant>> call = service.getRestaurants();
        call.enqueue(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurant, menu);
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
    public void onResponse(Response<List<Restaurant>> response) {
        dialog.dismiss();
        restaurants = response.body();

        final RecyclerView rv = (RecyclerView) findViewById(R.id.restaurantRyclerView);
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        rv.setLayoutManager(glm);

        final RestaurantAdapter adapter = new RestaurantAdapter(restaurants, this);
        rv.setAdapter(adapter);

        SearchView sv = (SearchView) findViewById(R.id.searchlocation);


        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                final List<Restaurant> filteredModelList = filter(restaurants, text);
                adapter.animateTo(filteredModelList);
                rv.scrollToPosition(0);
                return true;
            }

            private List<Restaurant> filter(List<Restaurant> restaurants, String query) {
                query = query.toLowerCase();
                final List<Restaurant> filteredModelList = new ArrayList<>();
                for (Restaurant r : restaurants) {
                    if (r.getCity().toLowerCase().contains(query) || String.valueOf(r.getPostal()).toLowerCase().contains(query)) {
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
                        startActivity(new Intent(getBaseContext(), MainActivity.class));
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}