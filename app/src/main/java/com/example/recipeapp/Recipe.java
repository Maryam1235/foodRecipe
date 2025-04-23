package com.example.recipeapp;

public class Recipe {
    private final int imageResId;
    private final String title;
    private final String description;
    private final String duration;

    public Recipe(int imageResId, String title, String description, String duration) {
        this.imageResId = imageResId;
        this.title = title;
        this.description = description;
        this.duration = duration;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }
}
