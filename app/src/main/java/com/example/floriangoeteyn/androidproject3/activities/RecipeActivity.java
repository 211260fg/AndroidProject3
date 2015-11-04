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

import java.util.List;

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

        final ProgressDialog dialog = ProgressDialog.show(this, "", "loading...");
        List<Recipe> recipes = recipeRepository.getRecipesFromServer();
        dialog.dismiss();
        if(recipes!=null&&recipes.size()!=0) {
            resultView.setText(recipes.toString());
        }else{
            new AlertDialog.Builder(this)
                    .setTitle("Connectie probleem")
                    .setMessage("Verbind met het internet om de recepten op te halen")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }})
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            if(resultView!=null) {
                resultView.setText("Verbind met het internet om de recepten op te halen");
            }
        }
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
