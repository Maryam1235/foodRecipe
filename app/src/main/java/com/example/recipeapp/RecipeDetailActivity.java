package com.example.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ImageView image = findViewById(R.id.detailImage);
        TextView title = findViewById(R.id.detailTitle);
        TextView description = findViewById(R.id.detailDescription);


        // Get the data from intent
        Intent intent = getIntent();
        int imageResId = intent.getIntExtra("imageResId", 0);
        String recipeTitle = intent.getStringExtra("title");
        String recipeDescription = intent.getStringExtra("description");
        String recipeDuration = intent.getStringExtra("duration");

        // Set the data to the views
        image.setImageResource(imageResId);
        title.setText(recipeTitle);
        description.setText(recipeDescription);

    }
}
