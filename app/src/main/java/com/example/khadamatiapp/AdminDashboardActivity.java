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

    // ربط الـ ViewBinding لسهولة الوصول لعناصر XML
    private ActivityAdminDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // تهيئة ViewBinding وربطه بالـ XML
        binding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // عرض جميع طلبات الخدمات عند فتح الصفحة
        displayRequests();

        // زر تسجيل الخروج
        binding.btnLogout.setOnClickListener(v -> {
            // إنشاء Intent للانتقال لصفحة تسجيل الدخول
            Intent intent = new Intent(this, LoginActivity.class);

            // مسح جميع الأنشطة السابقة من الـ Stack عند تسجيل الخروج
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            // بدء الصفحة الجديدة
            startActivity(intent);

            // إغلاق هذه الصفحة
            finish();
        });

    }

    // دالة لعرض جميع الطلبات المخزنة في SharedPreferences
    private void displayRequests() {

        // فتح SharedPreferences باسم UserPrefs
        SharedPreferences sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        // قراءة كل الطلبات المخزنة كمجموعة من النصوص
        Set<String> allRequests = sharedPref.getStringSet("all_requests_list", new HashSet<>());

        // التحقق إذا كانت القائمة فارغة
        if (allRequests.isEmpty()) {
            binding.tvRequestsList.setText("There are no requests currently."); // لا توجد طلبات حالياً
        } else {
            // إنشاء نص منسق لكل الطلبات لعرضها في TextView
            StringBuilder sb = new StringBuilder();
            for (String request : allRequests) {
                sb.append("• ").append(request).append("\n\n----------------\n\n");
            }
            // عرض جميع الطلبات في TextView
            binding.tvRequestsList.setText(sb.toString());
        }
    }
}
