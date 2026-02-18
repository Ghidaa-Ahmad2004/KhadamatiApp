package com.example.khadamatiapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.khadamatiapp.databinding.ItemServiceBinding; // استيراد البايندينج
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    private List<Service> serviceList;
    private Context context;

    public ServiceAdapter(List<Service> serviceList, Context context) {
        this.serviceList = serviceList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemServiceBinding binding = ItemServiceBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Service service = serviceList.get(position);

        holder.binding.serviceName.setText(service.getName());
        holder.binding.serviceImage.setImageResource(service.getImageResId());

        holder.binding.viewDetailsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("selected_service", service);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return serviceList.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemServiceBinding binding;

        public ViewHolder(ItemServiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}