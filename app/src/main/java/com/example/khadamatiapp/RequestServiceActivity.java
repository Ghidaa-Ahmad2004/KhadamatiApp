package com.example.khadamatiapp;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.khadamatiapp.databinding.ActivityRequestServiceBinding;

public class RequestServiceActivity extends AppCompatActivity {
    private ActivityRequestServiceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRequestServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSubmit.setOnClickListener(v -> {
            if (binding.etName.getText().toString().isEmpty() ||
                    binding.etPhone.getText().toString().isEmpty() ||
                    binding.etEmail.getText().toString().isEmpty() ||
                    binding.etLocation.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Service request submitted successfully", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}