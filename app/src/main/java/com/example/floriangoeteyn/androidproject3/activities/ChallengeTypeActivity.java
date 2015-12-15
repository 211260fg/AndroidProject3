package com.example.floriangoeteyn.androidproject3.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.floriangoeteyn.androidproject3.R;

public class ChallengeTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challengetype);

        TextView tvScore = (TextView)findViewById(R.id.tvScore);
        TextView tvUitdagingen = (TextView)findViewById(R.id.tvUitdagingen);
        TextView tvVoltooid = (TextView)findViewById(R.id.tvVoltooid);

        tvScore.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Mathlete-Skinny.otf"));
        tvUitdagingen.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Mathlete-Skinny.otf"));
        tvVoltooid.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Mathlete-Skinny.otf"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_challengetype, menu);
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

    public void showCookingChallenges(View view) {
        startActivity(new Intent(this, ChallengeChooserActivity.class));
    }

    public void showRestaurantChallenges(View view) {
        startActivity(new Intent(this, ChallengeChooserActivity.class));
    }
}
