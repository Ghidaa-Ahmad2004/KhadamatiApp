package com.example.khadamatiapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.khadamatiapp.databinding.ItemServiceBinding;
import java.util.List;

// Adapter لربط بيانات الخدمات مع RecyclerView
public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    // قائمة الخدمات
    private List<Service> serviceList;

    // سياق التطبيق
    private Context context;

    // Constructor لتمرير القائمة والسياق
    public ServiceAdapter(List<Service> serviceList, Context context) {
        this.serviceList = serviceList;
        this.context = context;
    }

    // --- الدالة الجديدة المطلوبة لعملية البحث (الفلترة) ---
    public void filterList(List<Service> filteredList) {
        this.serviceList = filteredList; // تحديث القائمة الحالية بالقائمة المفلترة
        notifyDataSetChanged(); // إبلاغ الـ RecyclerView بتحديث البيانات المعروضة
    }

    // إنشاء ViewHolder لكل عنصر في RecyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ربط XML لكل عنصر باستخدام ViewBinding
        ItemServiceBinding binding = ItemServiceBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    // ربط بيانات كل عنصر مع عناصر الواجهة
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // استرجاع الخدمة من القائمة حسب الموقع
        Service service = serviceList.get(position);

        // عرض اسم الخدمة في TextView
        holder.binding.serviceName.setText(service.getName());

        // عرض صورة الخدمة في ImageView
        holder.binding.serviceImage.setImageResource(service.getImageResId());

        // زر عرض التفاصيل
        holder.binding.viewDetailsBtn.setOnClickListener(v -> {
            // إنشاء Intent للانتقال إلى DetailActivity
            Intent intent = new Intent(context, DetailActivity.class);

            // تمرير كائن الخدمة المحددة باستخدام Serializable
            intent.putExtra("selected_service", service);

            // بدء صفحة التفاصيل
            context.startActivity(intent);
        });
    }

    // عدد العناصر في RecyclerView
    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    // ViewHolder لربط كل عنصر بالواجهة
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemServiceBinding binding;

        public ViewHolder(ItemServiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
