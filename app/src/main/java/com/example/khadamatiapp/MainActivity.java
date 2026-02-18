package com.example.khadamatiapp;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.khadamatiapp.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Service> list = new ArrayList<>();

        list.add(new Service("Carpenter", "A professional carpenter offers expert woodworking services including custom furniture design, wooden " +
                "furniture repair, installation of wooden doors and cabinets, interior finishing, and maintenance of all types of woodwork. He is known for his precise measurements," +
                " use of durable materials, and modern designs to ensure strong, elegant, and long-lasting results that meet your needs.", R.drawable.carpenter));
        list.add(new Service("Electrician", "Professional electrician providing reliable electrical installation, maintenance, and repair services for homes, offices, " +
                "and commercial facilities. Services include wiring installation, lighting systems setup, electrical panel upgrades, troubleshooting power failures, installing switches and outlets, " +
                "and ensuring all electrical systems meet safety standards. Dedicated to delivering efficient, safe, and long-lasting electrical solutions tailored to your needs.", R.drawable.electrician));
        list.add(new Service("Plumber", "Professional plumber offering comprehensive plumbing installation, maintenance, and repair services for residential and commercial properties. " +
                "Services include fixing water leaks, repairing and installing pipes, unclogging drains, installing sinks and faucets, bathroom and kitchen plumbing, water heater installation and maintenance, and full plumbing system inspections. " +
                "Committed to providing fast, reliable, and high-quality solutions that ensure safe water flow and long-lasting performance.", R.drawable.plumber));
        list.add(new Service("Blacksmith", "Professional blacksmith specializing in metal fabrication, welding, and custom iron works for residential and commercial projects. Services include designing and manufacturing metal doors and windows, " +
                "iron gates, railings, fences, metal frames, repairs, and structural welding. Skilled in working with various metals using modern tools and techniques to ensure strength, durability, " +
                "and high-quality finishing. Committed to delivering secure, precise, and long-lasting metal solutions tailored to your needs.", R.drawable.blacksmith));
        list.add(new Service("Tiles worker", "Professional tiles worker providing expert tile installation and finishing services for residential and commercial spaces. Services include floor and wall tiling, bathroom and kitchen tile installation, " +
                "ceramic and porcelain tiles, marble and stone fitting, tile replacement, grouting, and precise surface leveling. Skilled in accurate measurements," +
                " clean finishing, and modern design layouts to ensure durable, water-resistant, and visually appealing results that enhance the overall look of your space.", R.drawable.tilesworker));
        list.add(new Service("Teacher", "Professional teacher providing educational services, tutoring, and personalized guidance for students" +
                " of all levels. Services include lesson planning, exam preparation, subject-specific tutoring," +
                " and mentoring. Committed to creating a supportive and effective learning environment that " +
                "helps students achieve their academic goals.", R.drawable.teacher));
        list.add(new Service("Painter", "Professional painter providing high-quality interior and exterior painting services for homes, offices, and commercial buildings. Services include wall painting, surface preparation, crack filling, " +
                "sanding, decorative finishes, waterproof coatings, and color consultation to match modern designs. Uses high-quality materials and precise " +
                "techniques to ensure smooth finishing, long-lasting colors, and a clean professional appearance that enhances the beauty of your space.", R.drawable.painter));
        ServiceAdapter adapter = new ServiceAdapter(list, this);
        binding.recyclerView.setAdapter(adapter);
        binding.btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            finish();

        });
        binding.btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SplashActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            finish();
        });


    }
}