package com.example.recipeapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;

public class EditProfileActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
        setupCommonUI(); //

    }
}