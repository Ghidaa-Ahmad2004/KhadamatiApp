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

    // ربط الـ ViewBinding لسهولة الوصول لعناصر XML
    private ActivityRequestServiceBinding binding;

    // اسم SharedPreferences المستخدم لحفظ البيانات
    private static final String PREFS_NAME = "UserPrefs";

    // اسم الخدمة المختارة من الصفحة السابقة
    private String serviceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // تهيئة ViewBinding وربطه بالـ XML
        binding = ActivityRequestServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // استلام اسم الخدمة من Intent
        serviceName = getIntent().getStringExtra("service_name");

        // تحميل بيانات المستخدم المخزنة مسبقاً في الحقول
        loadUserData();

        // عند الضغط على زر الإرسال
        binding.btnSubmit.setOnClickListener(v -> {

            // قراءة البيانات المدخلة من المستخدم
            String name = binding.etName.getText().toString();
            String phone = binding.etPhone.getText().toString();
            String email = binding.etEmail.getText().toString();
            String location = binding.etLocation.getText().toString();

            // التحقق من أن كل الحقول ممتلئة
            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || location.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                // حفظ بيانات المستخدم في SharedPreferences
                saveUserData(name, phone, email);

                // إضافة الطلب إلى قائمة الطلبات المرسلة للأدمن
                addRequestForAdmin(name, phone, email, location, serviceName);

                // رسالة نجاح الطلب
                Toast.makeText(this, "Your request has been sent successfully\n", Toast.LENGTH_LONG).show();

                // إغلاق الصفحة بعد الإرسال
                finish();
            }
        });

        // زر تسجيل الخروج
        binding.btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            // مسح جميع الصفحات السابقة
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    // دالة لحفظ بيانات المستخدم في SharedPreferences
    private void saveUserData(String name, String phone, String email) {
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        sharedPref.edit()
                .putString("userName", name)
                .putString("userPhone", phone)
                .putString("userEmail", email)
                .apply();
    }

    // دالة لإضافة طلب الخدمة إلى SharedPreferences ليشاهده الأدمن
    private void addRequestForAdmin(String name, String phone, String email, String location, String service) {
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // قراءة الطلبات السابقة
        Set<String> allRequests = sharedPref.getStringSet("all_requests_list", new HashSet<>());
        Set<String> updatedRequests = new HashSet<>(allRequests);

        // إنشاء نص مفصل للطلب
        String requestDetails = "Service: " + (service != null ? service : "Not Specified") +
                "\nName: " + name +
                "\nPhone: " + phone +
                "\nEmail: " + email +
                "\nLocation: " + location;

        // إضافة الطلب الجديد إلى القائمة وحفظه
        updatedRequests.add(requestDetails);
        sharedPref.edit().putStringSet("all_requests_list", updatedRequests).apply();
    }

    // دالة لتحميل بيانات المستخدم المخزنة مسبقاً وعرضها في الحقول
    private void loadUserData() {
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        binding.etName.setText(sharedPref.getString("userName", ""));
        binding.etPhone.setText(sharedPref.getString("userPhone", ""));
        binding.etEmail.setText(sharedPref.getString("userEmail", ""));
    }
}
