package com.example.khadamatiapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.khadamatiapp.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startBtn.setOnClickListener(v -> {
            Toast.makeText(SplashActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);

            finish();

        });

    }
}