package com.example.floriangoeteyn.androidproject3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.facebook.FacebookSdk;

public class RegistreerActivity extends AppCompatActivity {

    // UI references.
    @Bind(R.id.email) AutoCompleteTextView emailView;
    @Bind(R.id.wachtwoord) EditText wachtwoordView;
    @Bind(R.id.wachtwoord_opnieuw) EditText wachtwoordOpnieuwView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_registreer);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void onClick(View view) {
        //TODO: LOGIN
    }
}

