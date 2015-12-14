package com.example.floriangoeteyn.androidproject3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.floriangoeteyn.androidproject3.domein.DomeinController;
import com.example.floriangoeteyn.androidproject3.domein.Gebruiker;
import com.example.floriangoeteyn.androidproject3.persistentie.RetrofitHelper;

import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.test) ImageButton test;
    @Bind(R.id.toolbar) Toolbar toolbar;

    private DomeinController dc;

    @OnClick(R.id.test)
    public void testje(View view) {

        try {
            Call<JSONObject> call = dc.test();

            call.enqueue(new Callback<JSONObject>() {
                @Override
                public void onResponse(Response<JSONObject> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        Toast.makeText(getApplicationContext(),
                                "yooooooooooooooo",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "" + response.code(),
                                Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getApplicationContext(),
                            "oeps",
                            Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),
                    ex.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoofdmenu);

        ButterKnife.bind(this);

        dc = DomeinController.getInstance();

        setGebruiker();

        setSupportActionBar(toolbar);
        View mActionBarView = getLayoutInflater().inflate(R.layout.my_action_bar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setCustomView(mActionBarView);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

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

        switch(item.getItemId()) {
            case(R.id.action_profiel):
                return true;

            case(R.id.action_logout):
                dc.setGebruiker(null);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void setGebruiker() {
        try {
            Call<Gebruiker> call = dc.getPersistentieGebruiker();

            call.enqueue(new Callback<Gebruiker>() {
                @Override
                public void onResponse(Response<Gebruiker> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        dc.setGebruiker(response.body());
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "" + response.code(),
                                Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getApplicationContext(),
                            t.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),
                    ex.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
