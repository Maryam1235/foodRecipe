package com.example.loginscreen;

import android.os.Bundle;
import android.widget.TextView;
import com.example.loginscreen.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends BaseActivity {

    TextView usernameTextView, emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupCommonUI();

        // Find the views
        usernameTextView = findViewById(R.id.username);
        emailTextView = findViewById(R.id.email);

        // Get current user from Firebase
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            // Display user email
            String email = currentUser.getEmail();
            emailTextView.setText(email);

            // You can display the part before '@' as a basic "username"
            String displayName = currentUser.getDisplayName();
            if (displayName == null || displayName.isEmpty()) {
                // fallback to part before @ if displayName not available
                displayName = email.split("@")[0];
            }
            usernameTextView.setText(displayName);
        }
    }
}
