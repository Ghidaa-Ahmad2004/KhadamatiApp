package com.example.khadamatiapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.khadamatiapp.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    // ربط الـ ViewBinding لسهولة الوصول لعناصر XML
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // تهيئة ViewBinding وربطه بالـ XML
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // عند الضغط على زر البداية
        binding.startBtn.setOnClickListener(v -> {

            // إظهار رسالة Toast لتأكيد تسجيل الدخول
            Toast.makeText(SplashActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();

            // الانتقال إلى MainActivity
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);

            // إغلاق هذه الصفحة لمنع العودة إليها
            finish();
        });
    }
}
