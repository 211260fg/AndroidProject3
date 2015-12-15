package com.example.floriangoeteyn.androidproject3.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.floriangoeteyn.androidproject3.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoofdmenu);
        Button btnChallenges = (Button)findViewById(R.id.btnChallenges);
        Button btnRestaurants = (Button)findViewById(R.id.btnRestaurants);
        Button btnRecipes = (Button)findViewById(R.id.btnRecipes);
        Button btnWorkshops = (Button)findViewById(R.id.btnWorkshops);
        Button btnPrestaties = (Button)findViewById(R.id.btnPrestaties);

        Typeface artistamp = Typeface.createFromAsset(getAssets(),  "fonts/ArtistampMedium.ttf");

        btnChallenges.setTypeface(artistamp);
        btnRestaurants.setTypeface(artistamp);
        btnRecipes.setTypeface(artistamp);
        btnWorkshops.setTypeface(artistamp);
        btnPrestaties.setTypeface(artistamp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void showRecipes(View view) {
        startActivity(new Intent(this, RecipeActivity.class));
    }

    public void showRestaurants(View view) {
        startActivity(new Intent(this, RestaurantActivity.class));
    }

    public void showChallenges(View view) {
        startActivity(new Intent(this, ChallengeTypeActivity.class));
    }
}
