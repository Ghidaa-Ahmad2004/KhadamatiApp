package com.example.khadamatiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.khadamatiapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    // ربط الـ ViewBinding لسهولة الوصول لعناصر XML
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // تهيئة ViewBinding وربطه بالـ XML
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // عند الضغط على زر تسجيل الدخول
        binding.btnLogin.setOnClickListener(v -> {

            // قراءة البريد الإلكتروني وكلمة المرور من EditText
            String email = binding.etEmail.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();

            // فتح SharedPreferences للتحقق من بيانات المستخدم المخزنة
            SharedPreferences prefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
            String savedEmail = prefs.getString("userEmail", "");
            String savedPassword = prefs.getString("userPassword", "");

            // التحقق من بيانات الأدمن
            if (email.equals("admin@khadamati.com") && password.equals("admin123")) {
                Toast.makeText(this, "Welcome Admin", Toast.LENGTH_SHORT).show();
                // الانتقال إلى لوحة تحكم الأدمن
                Intent intent = new Intent(this, AdminDashboardActivity.class);
                startActivity(intent);
                finish(); // إغلاق صفحة تسجيل الدخول
            }

            // التحقق من بيانات المستخدم العادي
            else if (!email.isEmpty() && email.equals(savedEmail) && password.equals(savedPassword)) {
                Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show();

                // الانتقال إلى MainActivity
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish(); // إغلاق صفحة تسجيل الدخول
            }

            // إذا كانت البيانات غير صحيحة
            else {
                Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
            }
        });

        // عند الضغط على زر الانتقال للتسجيل
        binding.btnGoToRegister.setOnClickListener(v -> {
            // الانتقال إلى صفحة إنشاء حساب
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
