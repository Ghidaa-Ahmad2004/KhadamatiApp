package com.example.khadamatiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.khadamatiapp.databinding.ActivityAdminDashboardBinding;
import java.util.HashSet;
import java.util.Set;

public class AdminDashboardActivity extends AppCompatActivity {
    private ActivityAdminDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        displayRequests();
        binding.btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            finish();
        });

    }

    private void displayRequests() {
        SharedPreferences sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        Set<String> allRequests = sharedPref.getStringSet("all_requests_list", new HashSet<>());

        if (allRequests.isEmpty()) {
            binding.tvRequestsList.setText("There are no requests currently.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String request : allRequests) {
                sb.append("• ").append(request).append("\n\n----------------\n\n");
            }
            binding.tvRequestsList.setText(sb.toString());
        }
    }
}