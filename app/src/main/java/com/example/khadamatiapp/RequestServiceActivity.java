package com.example.khadamatiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.khadamatiapp.databinding.ActivityRequestServiceBinding;
import java.util.HashSet;
import java.util.Set;

public class RequestServiceActivity extends AppCompatActivity {
    private ActivityRequestServiceBinding binding;
    private static final String PREFS_NAME = "UserPrefs";
    private String serviceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRequestServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        serviceName = getIntent().getStringExtra("service_name");

        loadUserData();

        binding.btnSubmit.setOnClickListener(v -> {
            String name = binding.etName.getText().toString();
            String phone = binding.etPhone.getText().toString();
            String email = binding.etEmail.getText().toString();
            String location = binding.etLocation.getText().toString();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || location.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                saveUserData(name, phone, email);

                addRequestForAdmin(name, phone, email, location, serviceName);

                Toast.makeText(this, "Your request has been sent successfully\n", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        binding.btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void saveUserData(String name, String phone, String email) {
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        sharedPref.edit()
                .putString("userName", name)
                .putString("userPhone", phone)
                .putString("userEmail", email)
                .apply();
    }

    private void addRequestForAdmin(String name, String phone, String email, String location, String service) {
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Set<String> allRequests = sharedPref.getStringSet("all_requests_list", new HashSet<>());
        Set<String> updatedRequests = new HashSet<>(allRequests);

        String requestDetails = "Service: " + (service != null ? service : "Not Specified") +
                "\nName: " + name +
                "\nPhone: " + phone +
                "\nEmail: " + email +
                "\nLocation: " + location;

        updatedRequests.add(requestDetails);
        sharedPref.edit().putStringSet("all_requests_list", updatedRequests).apply();
    }

    private void loadUserData() {
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        binding.etName.setText(sharedPref.getString("userName", ""));
        binding.etPhone.setText(sharedPref.getString("userPhone", ""));
        binding.etEmail.setText(sharedPref.getString("userEmail", ""));
    }
}