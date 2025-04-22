package com.example.recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private final Context context;
    private final List<Recipe> fullRecipeList;
    private final List<Recipe> filteredRecipeList;

    public RecipeAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.fullRecipeList = new ArrayList<>(recipeList); // For keeping original list
        this.filteredRecipeList = new ArrayList<>(recipeList); // For display & filter
    }

    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.ViewHolder holder, int position) {
        Recipe recipe = filteredRecipeList.get(position);
        holder.title.setText(recipe.getTitle());
        holder.description.setText(recipe.getDescription());
        holder.image.setImageResource(recipe.getImageResId());
    }

    @Override
    public int getItemCount() {
        return filteredRecipeList.size();
    }

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textDescription);
            image = itemView.findViewById(R.id.imageView);
        }
    }

    // Filter function
    public void filterList(String query) {
        filteredRecipeList.clear();
        if (query == null || query.trim().isEmpty()) {
            filteredRecipeList.addAll(fullRecipeList);
        } else {
            String lowerQuery = query.toLowerCase();
            for (Recipe recipe : fullRecipeList) {
                if (recipe.getTitle().toLowerCase().contains(lowerQuery) ||
                        recipe.getDescription().toLowerCase().contains(lowerQuery)) {
                    filteredRecipeList.add(recipe);
                }
            }
        }
        notifyDataSetChanged();
    }
}
