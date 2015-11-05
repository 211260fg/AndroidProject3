package com.example.floriangoeteyn.androidproject3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.example.floriangoeteyn.androidproject3.domein.DomeinController;
import com.example.floriangoeteyn.androidproject3.persistentie.FacebookGraphAPI;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.Profile;

public class RegistreerActivity extends AppCompatActivity {

    // UI references.
    @Bind(R.id.email) AutoCompleteTextView emailView;
    @Bind(R.id.wachtwoord) EditText wachtwoordView;
    @Bind(R.id.wachtwoord_opnieuw) EditText wachtwoordOpnieuwView;

    private DomeinController dc;

    AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        accessToken = AccessToken.getCurrentAccessToken();
        setContentView(R.layout.activity_registreer);
        ButterKnife.bind(this);

        dc = new DomeinController();

        if (Profile.getCurrentProfile() != null) {
            if (AccessToken.getCurrentAccessToken().getPermissions().contains("email")) {
                emailView.setText(dc.getFacebookInfo("email"));
            }
            else {
                Toast.makeText(getApplicationContext(), "email not permitted", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "not logged in", Toast.LENGTH_LONG).show();
        }

    }

    @OnClick(R.id.btnLogin)
    public void onClick(View view) {
        //TODO: LOGIN
    }
}

