package com.example.floriangoeteyn.androidproject3.persistentie;

import android.app.Activity;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.Serializable;

import java.util.List;

/**
 * Created by Jeroen on 4/11/2015.
 */
public class FacebookGraphAPI implements Serializable {

    private static JSONObject info;

    public FacebookGraphAPI() {

    }

    public void accessGraph() {

        // BETERE OPLOSSING ZOEKEN - huidig schijnt zeer slecht te zijn, interfereert met thread-policies.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        //Application code
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,gender,birthday");
        request.setParameters(parameters);
        info = request.executeAndWait().getJSONObject();
    }

    public String getInfo(String req) {
        try {
            return (String) info.get(req);
        } catch (Exception ex) {

        }
        return "";
    }
}
