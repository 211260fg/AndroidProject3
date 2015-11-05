package com.example.floriangoeteyn.androidproject3.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.floriangoeteyn.androidproject3.R;
import com.example.floriangoeteyn.androidproject3.models.Recipe;
import com.example.floriangoeteyn.androidproject3.models.RecipeRepository;
import com.example.floriangoeteyn.androidproject3.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class RecipeActivity extends AppCompatActivity {

    private TextView resultView;
    private RecipeRepository recipeRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        recipeRepository = new RecipeRepository();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Intent intent = getIntent();
        resultView = (TextView) findViewById(R.id.resultView);

        //final ProgressDialog dialog = ProgressDialog.show(this, "", "loading...");
        //List<Recipe> recipes = recipeRepository.getRecipesFromServer();

        RestClient.RecipeApiInterface service= RestClient.getClient();
        Call<List<Recipe>> call=service.getRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Response<List<Recipe>> response) {
                List<Recipe> result = response.body();
                if (response.isSuccess()) {
                    resultView.setText(result.toString());
                } else {
                    resultView.setText("geen geldige response");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                resultView.setText("geen connectie");

            }
        });


        //dialog.dismiss();
        //if(recipes!=null&&recipes.size()!=0) {
        //resultView.setText(recipes.toString());
        /*}else{
            resultView.setText("Verbind met het internet om de recepten op te halen");
            new AlertDialog.Builder(this)
                    .setTitle("Connectie probleem")
                    .setMessage("Verbind met het internet om de recepten op te halen")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }})
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }*/
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
}