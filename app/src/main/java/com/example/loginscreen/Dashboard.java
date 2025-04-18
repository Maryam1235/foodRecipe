package com.example.loginscreen;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
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

        // 🔍 Setup SearchView
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

        // 🖼️ Setup Image Slider
        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel(R.drawable.pizza1, "Delicious Pizza", null));
        imageList.add(new SlideModel(R.drawable.burger1, "Juicy Burger", null));
        imageList.add(new SlideModel(R.drawable.pasta1, "Creamy Pasta", null));

        // Set image list to the slider
        imageSlider.setImageList(imageList);

        // Start sliding every 3 seconds (3000 milliseconds)
        imageSlider.startSliding(3000);  // Time in milliseconds (3 seconds)

        // 🧾 Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recipeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        recipeList.add(new Recipe(R.drawable.pizza1, "Pizza", "1. Prepare dough\n2. Add toppings\n3. Bake in oven"));
        recipeList.add(new Recipe(R.drawable.burger1, "Burger", "1. Toast bun\n2. Add veggies and meat\n3. Serve hot"));
        recipeList.add(new Recipe(R.drawable.pasta1, "Pasta", "1. Boil pasta\n2. Add sauce\n3. Serve hot"));

        // Adapter
        adapter = new RecipeAdapter(this, recipeList);
        recyclerView.setAdapter(adapter);
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
