package com.example.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    EditText email;
    Button resetPasswordButton;
    TextView signupText;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_password);

        email = findViewById(R.id.email);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);
        signupText = findViewById(R.id.signupText);

        mAuth = FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(view -> {
            String emailAddress = email.getText().toString().trim();

            if (!emailAddress.isEmpty()) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgetPassword.this, "Check your email to reset your password", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(ForgetPassword.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            } else {
                Toast.makeText(ForgetPassword.this, "Enter your email address", Toast.LENGTH_SHORT).show();
            }

        });

        signupText.setOnClickListener(v -> {
            startActivity(new Intent(ForgetPassword.this, Signup.class));
        });
    }
}
