package com.example.recipeapp;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ExploreActivity extends BaseActivity {

    private boolean isUserNavigating = false;
    private RecipeAdapter adapter;
    private List<Recipe> recipeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore); // Make sure activity_explore.xml matches layout

        setupCommonUI();

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recipeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        recipeList.add(new Recipe(R.drawable.pizza, "Pizza", "1. Prepare dough\n2. Add toppings\n3. Bake in oven","Duration: 30 Minutes" ));
        recipeList.add(new Recipe(R.drawable.burgerposter, "Burger", "1. Toast bun\n2. Add veggies and meat\n3. Serve hot" , "Duration: 80 Minutes" ));
        recipeList.add(new Recipe(R.drawable.pasta2, "Pasta", "1. Boil the Pasta\n2. Add Tomato Sauce to make it better\n3. Add chilly for extra spicy\n4. Serve hot","Duration: 40 Minutes" ));
        recipeList.add(new Recipe(R.drawable.cake, "Cake", "1. Mix flour with milk and butter\n2. Stir until the mixture becomes smooth\n3. put in my bowl\n4. bake in the oven","Duration: 60 Minutes" ));

        // Set adapter
        adapter = new RecipeAdapter(this, recipeList);
        recyclerView.setAdapter(adapter);

        // Setup SearchView
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // Not needed
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filterList(newText);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        isUserNavigating = false;
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!isUserNavigating) {
            FirebaseAuth.getInstance().signOut();
        }
    }
}
