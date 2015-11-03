package com.example.floriangoeteyn.androidproject3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.facebook.FacebookSdk;

public class LoginActivity extends AppCompatActivity {

    // UI references.
    @Bind(R.id.email) AutoCompleteTextView emailView;
    @Bind(R.id.wachtwoord) EditText wachtwoordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void login(View view) {
        //TODO: LOGIN

        //success
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.login_registreer)
    public void registreer() {
        Intent intent = new Intent(LoginActivity.this, RegistreerActivity.class);
        startActivity(intent);
    }

}

