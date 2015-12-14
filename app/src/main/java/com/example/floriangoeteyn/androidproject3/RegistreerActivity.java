package com.example.floriangoeteyn.androidproject3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.floriangoeteyn.androidproject3.Fragments.GebruikersInfoFragment;
import com.example.floriangoeteyn.androidproject3.domein.DomeinController;
import com.example.floriangoeteyn.androidproject3.domein.Gebruiker;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.Profile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RegistreerActivity extends AppCompatActivity {

    // UI references.
    @Bind(R.id.email) EditText emailView;
    @Bind(R.id.wachtwoord) EditText wachtwoordView;
    @Bind(R.id.wachtwoord_opnieuw) EditText wachtwoordOpnieuwView;
    @Bind(R.id.registreerError) TextView registreerError;
    @Bind(R.id.btnRegistreer) Button btnRegistreer;
    private GebruikersInfoFragment infoFragment;
    private EditText gebruikersnaamView, geboortedatumView;
    private Spinner leefsituatieView, gezinsledenView, ervaringView;


    private DomeinController dc;

    AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_registreer);
        ButterKnife.bind(this);

        accessToken = AccessToken.getCurrentAccessToken();

        dc = DomeinController.getInstance();

        infoFragment =  (GebruikersInfoFragment) getSupportFragmentManager().findFragmentById(R.id.infoFragment);
        gebruikersnaamView = infoFragment.getGebruikersnaam();
        geboortedatumView = infoFragment.getGeboortedatum();
        leefsituatieView = infoFragment.getLeefsituatie();
        gezinsledenView = infoFragment.getGezinsleden();
        ervaringView = infoFragment.getErvaring();

        testWaarden();

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

    private void testWaarden() {
        emailView.setText("test@gmail.com");
        wachtwoordView.setText("a");
        wachtwoordOpnieuwView.setText("a");
        gebruikersnaamView.setText("tester");
    }

    @OnClick(R.id.btnRegistreer)
    public void onClick(View view) {
        btnRegistreerNotClickable();
        String wachtwoord = wachtwoordView.getText().toString();
        if (wachtwoord.equals(wachtwoordOpnieuwView.getText().toString())) {

            String email = emailView.getText().toString();
            String gebruikersnaam = gebruikersnaamView.getText().toString();
            String geboortedatum = geboortedatumView.getText().toString();;
            String leefsituatie = leefsituatieView.getSelectedItem().toString();
            int gezinsleden = Integer.parseInt(gezinsledenView.getSelectedItem().toString());
            int ervaring = ervaringView.getSelectedItemPosition();


            try {

                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date date = format.parse(geboortedatum);

                Toast.makeText(getApplicationContext(),
                        date.toString(),
                        Toast.LENGTH_LONG).show();

                Call<Gebruiker> call = dc.registreer(
                        email, wachtwoord, gebruikersnaam, date, leefsituatie, gezinsleden, ervaring);

                call.enqueue(new Callback<Gebruiker>() {
                    @Override
                    public void onResponse(Response<Gebruiker> response, Retrofit retrofit) {
                        if (response.isSuccess()) {

                            //eerst nog opvragen van informatie!
                            Intent intent = new Intent(RegistreerActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            registreerError.setText(bepaalErrorBoodschap(response.code()));
                        }
                        btnRegistreerClickable();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        registreerError.setText(getString(R.string.registreer_error_default));
                        btnRegistreerClickable();
                    }
                });

            } catch (IOException | ParseException ex) {
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
                    Intent intent = new Intent(RegistreerActivity.this, GebruikersInfoFragment.class);
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
            registreerError.setText(getString(R.string.registreer_error_wachtwoorden));
            btnRegistreerClickable();
        }
    }

    private String bepaalErrorBoodschap(int code) {
        switch (code) {
            case 400: return getString(R.string.registreer_error_400);
            case 500: return getString(R.string.registreer_error_500);
            default: return getString(R.string.registreer_error_default);
        }
    }

    private void btnRegistreerClickable() {
        btnRegistreer.setClickable(true);
        btnRegistreer.setBackgroundResource(R.drawable.button_sign_in);
    }

    private void btnRegistreerNotClickable() {
        btnRegistreer.setClickable(false);
        btnRegistreer.setBackgroundResource(R.drawable.button_sign_in_disabled);
    }


}

