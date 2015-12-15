package com.example.floriangoeteyn.androidproject3.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.floriangoeteyn.androidproject3.R;

public class ChallengeViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challengeviewer);

        TextView tvScore = (TextView)findViewById(R.id.tvScore);
        TextView tvUitdaging = (TextView)findViewById(R.id.tvUitdaging);
        TextView tvVoltooid = (TextView)findViewById(R.id.tvVoltooid);

        tvScore.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Mathlete-Skinny.otf"));
        tvUitdaging.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Mathlete-Skinny.otf"));
        tvVoltooid.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Mathlete-Skinny.otf"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_challenge_viewer, menu);
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
