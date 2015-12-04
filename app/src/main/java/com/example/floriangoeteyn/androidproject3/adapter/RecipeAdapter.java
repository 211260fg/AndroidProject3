package com.example.floriangoeteyn.androidproject3.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.floriangoeteyn.androidproject3.R;
import com.example.floriangoeteyn.androidproject3.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{


    private List<Recipe> recipes;
    Context context;

    public RecipeAdapter(List<Recipe> recipes, Context context){
        this.context = context;
        this.recipes=new ArrayList<>(recipes);
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_recipe, viewGroup, false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder recipeViewHolder, int i) {
        recipeViewHolder.recipeTitle.setText(recipes.get(i).getTitle());
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


    public void animateTo(List<Recipe> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Recipe> newModels) {
        for (int i = recipes.size() - 1; i >= 0; i--) {
            final Recipe model = recipes.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Recipe> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Recipe model = newModels.get(i);
            if (!recipes.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Recipe> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Recipe model = newModels.get(toPosition);
            final int fromPosition = recipes.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Recipe removeItem(int position) {
        final Recipe model = recipes.remove(position);
        notifyItemRemoved(position);
        return model;
    }
    public void addItem(int position, Recipe model) {
        recipes.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Recipe model = recipes.remove(fromPosition);
        recipes.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
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