package com.example.floriangoeteyn.androidproject3.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.floriangoeteyn.androidproject3.R;
import com.example.floriangoeteyn.androidproject3.models.Recipe;
import com.example.floriangoeteyn.androidproject3.models.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {


    List<Restaurant> restaurants;
    Context context;

    public RestaurantAdapter(List<Restaurant> restaurants, Context context) {
        this.context = context;
        this.restaurants = restaurants;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_restaurant, viewGroup, false);
        return new RestaurantViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder restaurantViewHolder, int i) {
        restaurantViewHolder.restaurantNaam.setText(restaurants.get(i).getName());
        restaurantViewHolder.restaurantAdres.setText(restaurants.get(i).getAddress());
        restaurantViewHolder.restaurantStad.setText(restaurants.get(i).getCity());
        String features = "";
        for (String feature : restaurants.get(i).getFeatures()) {
            features += feature + "\n";
        }
        restaurantViewHolder.restaurantFeatures.setText(features);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView restaurantNaam;
        TextView restaurantAdres;
        TextView restaurantStad;
        TextView restaurantFeatures;

        RestaurantViewHolder(View itemView, Context context) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.restaurantCardView);
            restaurantNaam = (TextView) itemView.findViewById(R.id.restaurantNaam);
            restaurantAdres = (TextView) itemView.findViewById(R.id.restaurantAdres);
            restaurantStad = (TextView) itemView.findViewById(R.id.restaurantStad);
            restaurantFeatures = (TextView) itemView.findViewById(R.id.restaurantFeatures);
            cv.setTag(restaurantNaam.getText());
        }
    }

}