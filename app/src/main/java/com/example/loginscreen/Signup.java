package com.example.loginscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    EditText username, email, password, retypePassword;
    TextView loginText;
    Button signupButton;

    FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        // Initialize views
        username = findViewById(R.id.username); // Optional: for display purposes only
        email = findViewById(R.id.email);       // Email used for Firebase authentication
        password = findViewById(R.id.password);
        retypePassword = findViewById(R.id.retypePassword);
        signupButton = findViewById(R.id.signupButton);
        loginText = findViewById(R.id.loginText);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailInput = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String retypePass = retypePassword.getText().toString().trim();

                if (emailInput.isEmpty() || pass.isEmpty() || retypePass.isEmpty()) {
                    Toast.makeText(Signup.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(retypePass)) {
                    Toast.makeText(Signup.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else if (pass.length() < 6) {
                    Toast.makeText(Signup.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                } else {
                    // Register user with Firebase Auth
                    mAuth.createUserWithEmailAndPassword(emailInput, pass)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Signup.this, MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(Signup.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });

        // Redirect to Login Page
        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(Signup.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
