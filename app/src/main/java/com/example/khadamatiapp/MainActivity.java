package com.example.khadamatiapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.khadamatiapp.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ServiceAdapter adapter;
    private List<Service> list;      // القائمة التي سيتم تعديلها وعرضها
    private List<Service> fullList;  // نسخة ثابتة من القائمة الأصلية للبحث فيها

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // تهيئة ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // إعداد RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // إنشاء البيانات
        list = new ArrayList<>();
        list.add(new Service("Carpenter", "A professional carpenter offers expert woodworking services including custom furniture design, wooden furniture repair, installation of wooden doors and cabinets, interior finishing, and maintenance of all types of woodwork.", R.drawable.carpenter));
        list.add(new Service("Electrician", "Professional electrician providing reliable electrical installation, maintenance, and repair services for homes, offices, and commercial facilities.", R.drawable.electrician));
        list.add(new Service("Plumber", "Professional plumber offering comprehensive plumbing installation, maintenance, and repair services for residential and commercial properties.", R.drawable.plumber));
        list.add(new Service("Blacksmith", "Professional blacksmith specializing in metal fabrication, welding, and custom iron works for residential and commercial projects.", R.drawable.blacksmith));
        list.add(new Service("Tiles worker", "Professional tiles worker providing expert tile installation and finishing services for residential and commercial spaces.", R.drawable.tilesworker));
        list.add(new Service("Teacher", "Professional teacher providing educational services, tutoring, and personalized guidance for students of all levels.", R.drawable.teacher));
        list.add(new Service("Painter", "Professional painter providing high-quality interior and exterior painting services for homes, offices, and commercial buildings.", R.drawable.painter));

        // الاحتفاظ بنسخة كاملة لعملية الفلترة (مهم جداً للبحث)
        fullList = new ArrayList<>(list);

        // إعداد الأدبتر
        adapter = new ServiceAdapter(list, this);
        binding.recyclerView.setAdapter(adapter);

        // --- برمجة ميزة البحث (Filter) ---
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // استدعاء دالة الفلترة عند كل تغيير في النص
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // --- برمجة زر تسجيل الخروج (Logout) بشكل صحيح بدون تكرار ---
        binding.btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            // مسح جميع الصفحات السابقة لضمان خروج المستخدم بالكامل
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    // دالة الفلترة للبحث عن الخدمات
    private void filter(String text) {
        List<Service> filteredList = new ArrayList<>();

        for (Service item : fullList) {
            // التحقق إذا كان اسم الخدمة يحتوي على النص المكتوب (مع تجاهل حالة الأحرف)
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        // تحديث القائمة في الأدبتر بالنتائج الجديدة
        // ملاحظة: يجب أن تحتوي ServiceAdapter على دالة اسمها filterList
        adapter.filterList(filteredList);
    }
}
