package com.example.khadamatiapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.khadamatiapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    // ربط الـ ViewBinding لسهولة الوصول لعناصر XML
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // تهيئة ViewBinding وربطه بالـ XML
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // استلام بيانات الخدمة المختارة من الصفحة السابقة
        // البيانات مرسلة باستخدام Serializable
        Service service = (Service) getIntent().getSerializableExtra("selected_service");

        // التحقق إذا البيانات موجودة قبل عرضها
        if (service != null) {
            // عرض صورة الخدمة في ImageView
            binding.detailImage.setImageResource(service.getImageResId());
            // عرض اسم الخدمة
            binding.detailName.setText(service.getName());
            // عرض وصف الخدمة
            binding.detailDesc.setText(service.getDescription());
        }

        // زر طلب الخدمة
        binding.requestServiceBtn.setOnClickListener(v -> {
            // إنشاء Intent للانتقال إلى RequestServiceActivity
            Intent intent = new Intent(this, RequestServiceActivity.class);

            // تمرير اسم الخدمة إلى الصفحة التالية
            if (service != null) {
                intent.putExtra("service_name", service.getName());
            }

            // بدء الصفحة الجديدة
            startActivity(intent);
        });

        // زر تسجيل الخروج
        binding.btnLogout.setOnClickListener(v -> {
            // الانتقال إلى صفحة تسجيل الدخول
            Intent intent = new Intent(DetailActivity.this, LoginActivity.class);

            // مسح كل الصفحات السابقة من الـ Stack عند تسجيل الخروج
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            // بدء الصفحة الجديدة
            startActivity(intent);

            // إغلاق هذه الصفحة
            finish();
        });
    }
}
