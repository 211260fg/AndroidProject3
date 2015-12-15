package com.example.floriangoeteyn.androidproject3.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.floriangoeteyn.androidproject3.R;
import com.example.floriangoeteyn.androidproject3.models.Restaurant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Restaurant restaurant;

    TextView restaurantNaam;
    TextView restaurantAdres;
    TextView restaurantCity;
    TextView restaurantWebsite;
    TextView restaurantDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantdetails);

        restaurant = (Restaurant) this.getIntent().getSerializableExtra("restaurant");


        restaurantNaam = (TextView)findViewById(R.id.restaurantNaam);
        restaurantAdres = (TextView)findViewById(R.id.restaurantAdres);
        restaurantCity = (TextView)findViewById(R.id.restaurantCity);
        restaurantWebsite = (TextView)findViewById(R.id.restaurantWebsite);
        restaurantDesc = (TextView)findViewById(R.id.restaurantDesc);


        restaurantNaam.setText(restaurant.getName());
        restaurantAdres.setText(restaurant.getAddress());
        restaurantCity.setText(restaurant.getCity());
        restaurantWebsite.setText(Html.fromHtml("<a href=\""+restaurant.getWebsite_url()+"\">"+restaurant.getWebsite_url()+"</a>"));
        restaurantDesc.setText(restaurant.getDesc_long());

        restaurantWebsite.setMovementMethod(LinkMovementMethod.getInstance());

        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }



    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        LatLng positie = new LatLng(restaurant.getLat(), restaurant.getLng());

        mMap.addMarker(new MarkerOptions().position(positie).title(restaurant.getName()));
        float zoomLevel = (float) 14.0; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(positie, zoomLevel));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurant_details, menu);
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
