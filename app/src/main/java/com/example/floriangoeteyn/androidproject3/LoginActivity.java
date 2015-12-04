package com.example.floriangoeteyn.androidproject3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import com.example.floriangoeteyn.androidproject3.domein.DomeinController;
import com.example.floriangoeteyn.androidproject3.domein.Gebruiker;
import com.example.floriangoeteyn.androidproject3.persistentie.HerokuService;
import com.example.floriangoeteyn.androidproject3.persistentie.RetrofitHelper;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.okhttp.RequestBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    // UI references.
    @Bind(R.id.email) AutoCompleteTextView emailView;
    @Bind(R.id.wachtwoord) EditText wachtwoordView;
    @Bind(R.id.btnFacebookLogin) LoginButton btnFacebookLogin;
    @Bind(R.id.btnLogin) Button btnLogin;
    @Bind(R.id.loginError) TextView loginError;

    private DomeinController dc;

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        callbackManager = CallbackManager.Factory.create();

        /* if (AccessToken.getCurrentAccessToken() == null) {
            goToMain();
        } */

        dc = DomeinController.getInstance();

        List<String> permissions = Arrays.asList("email", "public_profile");
        btnFacebookLogin.setReadPermissions(permissions);
        btnFacebookLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                Intent intent = new Intent(LoginActivity.this, RegistreerActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel()
            {
                System.out.println("onCancel");
            }

            @Override
            public void onError(FacebookException exception)
            {
                Log.v("LoginActivity", exception.getCause().toString());
                Toast.makeText(getApplicationContext(), R.string.login_error_default, Toast.LENGTH_LONG).show();
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.btnLogin)
    public void login(View view) {
        btnLoginNotClickable();
        String email = emailView.getText().toString();
        String wachtwoord = wachtwoordView.getText().toString();

        try {
            Call<Gebruiker> call = dc.login(email, wachtwoord);

            call.enqueue(new Callback<Gebruiker>() {
                @Override
                public void onResponse(Response<Gebruiker> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        dc.setToken(response.body().getToken());

                        loginError.setText("");
                        goToMain();
                    } else {
                        loginError.setText(bepaalErrorBoodschap(response.code()));

                    }

                    btnLoginClickable();
                }

                @Override
                public void onFailure(Throwable t) {
                    loginError.setText(getString(R.string.login_error_default));
                    btnLoginClickable();
                }
            });
        } catch (IOException ex) {
            Toast.makeText(getApplicationContext(),
                    ex.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.login_registreer)
    public void registreer() {
        Intent intent = new Intent(LoginActivity.this, RegistreerActivity.class);
        startActivity(intent);
    }

    private void goToMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        /* if (AccessToken.getCurrentAccessToken() == null) {
            goToMain();
        } */
    }

    private String bepaalErrorBoodschap(int code) {
        switch (code) {
            case 400: return getString(R.string.login_error_400);
            case 401: return getString(R.string.login_error_401);
            default: return getString(R.string.login_error_default);
        }
    }

    private void btnLoginClickable() {
        btnLogin.setClickable(true);
        btnLogin.setBackgroundResource(R.drawable.button_sign_in);
    }

    private void btnLoginNotClickable() {
        btnLogin.setClickable(false);
        btnLogin.setBackgroundResource(R.drawable.button_sign_in_disabled);
    }

}

