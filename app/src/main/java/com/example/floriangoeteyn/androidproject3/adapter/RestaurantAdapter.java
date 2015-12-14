package com.example.floriangoeteyn.androidproject3.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.floriangoeteyn.androidproject3.R;
import com.example.floriangoeteyn.androidproject3.activities.RecipeActivity;
import com.example.floriangoeteyn.androidproject3.activities.RecipeDetailsActivity;
import com.example.floriangoeteyn.androidproject3.activities.RestaurantDetailsActivity;
import com.example.floriangoeteyn.androidproject3.models.Recipe;
import com.example.floriangoeteyn.androidproject3.models.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {


    private List<Restaurant> restaurants;
    private Context context;


    public RestaurantAdapter(List<Restaurant> restaurants, Context context) {
        this.restaurants = new ArrayList<>(restaurants);
        this.context=context;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_restaurant, viewGroup, false);
        RestaurantViewHolder vh = new RestaurantViewHolder(v, new RestaurantViewHolder.IRestaurantViewHolderClicks() {

            @Override
            public void getDetails(View caller) {


            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder restaurantViewHolder, int i) {
        final Restaurant r = restaurants.get(i);
        restaurantViewHolder.restaurantNaam.setText(r.getName());
        restaurantViewHolder.restaurantAdres.setText(r.getAddress());
        restaurantViewHolder.restaurantStad.setText(r.getCity());
        String features = "";
        for (String feature : r.getFeatures()) {
            features += feature + "\n";
        }
        restaurantViewHolder.restaurantFeatures.setText(features);
        restaurantViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RestaurantDetailsActivity.class);
                Bundle b = new Bundle();
                intent.putExtra("restaurant", r);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }


    public void animateTo(List<Restaurant> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Restaurant> newModels) {
        for (int i = restaurants.size() - 1; i >= 0; i--) {
            final Restaurant model = restaurants.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Restaurant> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Restaurant model = newModels.get(i);
            if (!restaurants.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Restaurant> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Restaurant model = newModels.get(toPosition);
            final int fromPosition = restaurants.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Restaurant removeItem(int position) {
        final Restaurant model = restaurants.remove(position);
        notifyItemRemoved(position);
        return model;
    }
    public void addItem(int position, Restaurant model) {
        restaurants.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Restaurant model = restaurants.remove(fromPosition);
        restaurants.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView cv;
        private TextView restaurantNaam;
        private TextView restaurantAdres;
        private TextView restaurantStad;
        private TextView restaurantFeatures;
        private IRestaurantViewHolderClicks listener;


        RestaurantViewHolder(View itemView, IRestaurantViewHolderClicks listener) {
            super(itemView);
            this.listener=listener;
            cv = (CardView) itemView.findViewById(R.id.restaurantCardView);
            restaurantNaam = (TextView) itemView.findViewById(R.id.restaurantNaam);
            restaurantAdres = (TextView) itemView.findViewById(R.id.restaurantAdres);
            restaurantStad = (TextView) itemView.findViewById(R.id.restaurantStad);
            restaurantFeatures = (TextView) itemView.findViewById(R.id.restaurantFeatures);
            cv.setTag(restaurantNaam.getText());
        }

        @Override
        public void onClick(View v) {
            listener.getDetails(v);
        }

        public static interface IRestaurantViewHolderClicks {
            public void getDetails(View caller);
        }
    }

}