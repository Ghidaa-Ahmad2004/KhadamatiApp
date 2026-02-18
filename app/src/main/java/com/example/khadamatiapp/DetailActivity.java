package com.example.khadamatiapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.khadamatiapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Service service = (Service) getIntent().getSerializableExtra("selected_service");

        if (service != null) {
            binding.detailImage.setImageResource(service.getImageResId());
            binding.detailName.setText(service.getName());
            binding.detailDesc.setText(service.getDescription());
        }

        binding.requestServiceBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, RequestServiceActivity.class);
            if (service != null) {
                intent.putExtra("service_name", service.getName());
            }
            startActivity(intent);
        });

        binding.btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, LoginActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            finish();
        });
    }
}