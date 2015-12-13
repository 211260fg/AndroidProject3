package com.example.floriangoeteyn.androidproject3.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.floriangoeteyn.androidproject3.R;
import com.example.floriangoeteyn.androidproject3.models.Ingredient;
import com.example.floriangoeteyn.androidproject3.models.Recipe;

import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity {

    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipedetails);
        ImageView recipeImage = (ImageView)findViewById(R.id.recipeImage);
        TextView recipeTitle = (TextView)findViewById(R.id.recipeTitle);
        ListView lvIngredients = (ListView) findViewById(R.id.lvIngredients);
        TextView recipeDesc = (TextView) findViewById(R.id.recipeDesc);
        //TextView recipeQuote = (TextView) findViewById(R.id.recipeQuote);
        //TextView recipeTagline = (TextView) findViewById(R.id.recipeTagline);
        recipe = (Recipe) this.getIntent().getSerializableExtra("recipe");

        recipeTitle.setText(recipe.getTitle());

        ArrayList<String> ingredients = new ArrayList<>();
        for(Ingredient i: recipe.getIngredients()){
            ingredients.add(i.getValue() + i.getIngredient());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, ingredients);

        lvIngredients.setAdapter(adapter);

        if(recipe.getImages()!=null&&recipe.getImages().size()!=0) {
            Glide.with(this).load(recipe.getImages().get(0).getUrl()).into(recipeImage);
        }

        /*
        recipeDesc.setText(recipe.getDesc());
        recipeQuote.setText(recipe.getQuote());
        recipeTagline.setText(recipe.getTagline());*/

        String recipeInfo = "";

        if(recipe.getTagline()!=null&&!recipe.getTagline().equals("")){
            recipeInfo +=recipe.getTagline()+"\n";
        }else if(recipe.getDesc()!=null&&!recipe.getDesc().equals("")){
            recipeInfo +=recipe.getDesc()+"\n";
        }else if(recipe.getQuote()!=null&&!recipe.getQuote().equals("")){
            recipeInfo +=recipe.getDesc()+"\n";
        }

        recipeDesc.setText(recipeInfo);
        recipeDesc.setMovementMethod(new ScrollingMovementMethod());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe_details, menu);
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
