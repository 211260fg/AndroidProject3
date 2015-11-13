package com.example.floriangoeteyn.androidproject3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import com.example.floriangoeteyn.androidproject3.domein.DomeinController;
import com.example.floriangoeteyn.androidproject3.persistentie.DictionaryOpenHelper;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.Profile;

import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

        dc = DomeinController.getInstance();

        if (Profile.getCurrentProfile() != null) {
            if (AccessToken.getCurrentAccessToken().getPermissions().contains("email")) {
                emailView.setText(dc.getFacebookInfo("email"));
            }
            else {
                Toast.makeText(getApplicationContext(), "emailrechten niet toegestaan", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "niet ingelogd", Toast.LENGTH_LONG).show();
        }

    }

    @OnClick(R.id.btnLogin)
    public void onClick(View view) {
        String wachtwoord = wachtwoordView.getText().toString();
        if (wachtwoord.equals(wachtwoordOpnieuwView.getText().toString())) {

            String email = emailView.getText().toString();

            try {
                Call<JSONObject> call = dc.registreer(email, wachtwoord);

                call.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Response<JSONObject> response, Retrofit retrofit) {
                        if (response.isSuccess()) {
                            //TODO: "token" opslaan, kan dit response.rawResponse.handshake.ciphersuite zijn??

                            //eerst nog opvragen van informatie!
                            Intent intent = new Intent(RegistreerActivity.this, GebruikersInfoActivity.class);
                            startActivity(intent);

                        } else {
                            switch (response.code()) {
                                case 400:
                                    Toast.makeText(getApplicationContext(),
                                            "Niet alle velden zijn ingevuld!",
                                            Toast.LENGTH_LONG).show();
                                case 500:
                                    Toast.makeText(getApplicationContext(),
                                            "Deze email is reeds ingenomen :(",
                                            Toast.LENGTH_LONG).show();
                                default: {
                                    Toast.makeText(getApplicationContext(),
                                            "Oeps, er ging iets fout, sorry hiervoor! Probeer later nog eens.",
                                            Toast.LENGTH_LONG).show();
                                    Log.e("responseError", "" + response.code());
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getApplicationContext(),
                                "Oeps, er ging iets fout, sorry hiervoor! Probeer later nog eens.",
                                Toast.LENGTH_LONG).show();
                        Log.d("LoginActivity", t.getMessage());
                    }
                });

            } catch (IOException ex) {
                Toast.makeText(getApplicationContext(),
                        ex.getMessage(),
                        Toast.LENGTH_LONG).show();
            }

            //region SPELEN MET SQLITE ?! behoort hier niet maar zonde van code!
            /*
            SQLiteDatabase db = new DictionaryOpenHelper(getApplicationContext()).getWritableDatabase();

            String[] columns = new String[1];
            columns[0] = "email";
            Cursor c = db.query(
                    "gebruiker",
                    columns,
                    "email=?",
                    columns,
                    null,
                    null,
                    "" );

            if (c.getColumnCount() != 0) {

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Calendar cal = Calendar.getInstance();
                String datum = dateFormat.format(cal.getTime());

                ContentValues values = new ContentValues();
                values.put("email", email);
                values.put("wachtwoord", wachtwoordView.getText().toString());
                values.put("dagGestart", datum);
                if (accessToken != null) {
                    values.put("facebookId", accessToken.getToken());
                }

                if (dc.getGebruiker().getGeboorteDatum() == null) {
                    Intent intent = new Intent(RegistreerActivity.this, GebruikersInfoActivity.class);
                    intent.putExtra("dc", dc);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(RegistreerActivity.this, MainActivity.class);
                    intent.putExtra("dc", dc);
                    startActivity(intent);
                }
                */
            //endregion

        } else {
            Toast.makeText(getApplicationContext(),
                    "de wachtwoorden komen niet overeen",
                    Toast.LENGTH_LONG).show();
        }
    }

}

