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
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{


    List<Recipe> recipes;
    Context context;
    public RecipeAdapter(List<Recipe> recipes, Context context){
        this.context = context;
        this.recipes=recipes;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_recipe, viewGroup, false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder recipeViewHolder, int i) {
        recipeViewHolder.recipeTitle.setText(recipes.get(i).getTitle());
        //recipeViewHolder.recipeImage.setImageResource(recipes.get(i).getImages().get(0).getUrl()));
        recipeViewHolder.recipeImage.setImageResource(R.mipmap.recepten);
        if(recipes.get(i).getImages()!=null&&recipes.get(i).getImages().size()!=0) {
            Picasso.with(context).load(recipes.get(i).getImages().get(0).getUrl()).into(recipeViewHolder.recipeImage);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView recipeTitle;
        ImageView recipeImage;

        RecipeViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.recipeCardView);
            recipeTitle = (TextView)itemView.findViewById(R.id.recipeTitle);
            recipeImage = (ImageView)itemView.findViewById(R.id.recipeImage);
        }
    }

}