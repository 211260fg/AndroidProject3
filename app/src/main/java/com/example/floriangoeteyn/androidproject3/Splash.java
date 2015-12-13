package com.example.floriangoeteyn.androidproject3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.floriangoeteyn.androidproject3.activities.MainActivity;

/**
 * Created by Robin on 16/10/2015.
 */
public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(0);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };

        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
