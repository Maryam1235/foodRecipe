package com.example.recipeapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;

public class ContactsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacts);
        setupCommonUI();
    }
}