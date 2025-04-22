package com.example.recipeapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;

public class SettingsActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
       setupCommonUI();
    }
}