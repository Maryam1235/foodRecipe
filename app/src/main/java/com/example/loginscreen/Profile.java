package com.example.loginscreen;

import android.os.Bundle;
import com.example.loginscreen.BaseActivity;


public class Profile extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupCommonUI();
    }
}
