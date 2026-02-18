package com.example.khadamatiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.khadamatiapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();

            SharedPreferences prefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
            String savedEmail = prefs.getString("userEmail", "");
            String savedPassword = prefs.getString("userPassword", "");

            if (email.equals("admin@khadamati.com") && password.equals("admin123")) {
                Toast.makeText(this, "Welcome Admin", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, AdminDashboardActivity.class);
                startActivity(intent);
                finish();
            }

            else if (!email.isEmpty() && email.equals(savedEmail) && password.equals(savedPassword)) {
                Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            else {
                Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnGoToRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}