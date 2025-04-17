package com.example.loginscreen;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends BaseActivity {

    private boolean isUserNavigating = false;
    private RecipeAdapter adapter;
    private List<Recipe> recipeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setupCommonUI();

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recipeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        recipeList.add(new Recipe(R.drawable.pizza1, "Pizza", "1. Prepare dough\n2. Add toppings\n3. Bake in oven"));
        recipeList.add(new Recipe(R.drawable.burger1, "Burger", "1. Toast bun\n2. Add veggies and meat\n3. Serve hot"));
        recipeList.add(new Recipe(R.drawable.pasta1, "Pasta", "1. Boil the Pasta\n2. Add Tomato Sauce to make it better\n3. Add chilly for extra spicy\n4. Serve hot"));

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
