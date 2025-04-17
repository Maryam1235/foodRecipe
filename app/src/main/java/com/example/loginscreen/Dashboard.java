package com.example.loginscreen;

import android.os.Bundle;
import com.example.loginscreen.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;


public class Dashboard extends BaseActivity {
    private boolean isUserNavigating = false; // Track if user is navigating manually
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setupCommonUI();

    }


    @Override
    public void onBackPressed() {
        isUserNavigating = false;
        super.onBackPressed(); // User exits app, not navigating within app
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Logout only if user is not navigating to another screen within the app
        if (!isUserNavigating) {
            FirebaseAuth.getInstance().signOut();
        }
    }

}
