package com.example.khadamatiapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.khadamatiapp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegisterSubmit.setOnClickListener(v -> {
            String name = binding.etRegName.getText().toString();
            String email = binding.etRegEmail.getText().toString();
            String password = binding.etRegPassword.getText().toString();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill out all data\n", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences prefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("userName", name);
                editor.putString("userEmail", email);
                editor.putString("userPassword", password);
                editor.apply();

                Toast.makeText(this, "Account created successfully! Log in now\n", Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }
}