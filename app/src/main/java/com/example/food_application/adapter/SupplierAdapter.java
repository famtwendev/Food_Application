package com.example.food_application.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_application.R;
import com.example.models.SupplierModels;

import java.util.ArrayList;

public class SupplierAdapter extends  RecyclerView.Adapter<SupplierAdapter.ViewHolder> {


    ArrayList<SupplierModels> supplierList;

    public SupplierAdapter(ArrayList<SupplierModels> supplierList) {
        this.supplierList = supplierList;
    }

    @Override
    public SupplierAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_supplier, parent, false);
        return new SupplierAdapter.ViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull SupplierAdapter.ViewHolder holder, int position) {
        holder.txtNameSupplier.setText(supplierList.get(position).getNameSupplier());

        holder.txtratingSupplier.setText(String.valueOf(supplierList.get(position).getRatingSupplier()));

        holder.txtdistanceSupplier.setText(String.format("%.1fkm",supplierList.get(position).getDistanceSupplier()));

        holder.txttimeSupplier.setText(String.valueOf(supplierList.get(position).getTimeSupplier()+"phút"));
//        holder.itemCategoryLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background));

        holder.imvPhotoSupplier.setImageResource(supplierList.get(position).getPictureSupplier());
    }

    @Override
    public int getItemCount() {
        return supplierList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvPhotoSupplier;
        TextView txtNameSupplier;
        TextView txtratingSupplier;
        TextView txtdistanceSupplier;
        TextView txttimeSupplier;
        LinearLayout itemSupplierLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameSupplier = itemView.findViewById(R.id.txtNameSupplier);
            imvPhotoSupplier = itemView.findViewById(R.id.imvPhotoSupplier);
            txtratingSupplier = itemView.findViewById(R.id.txtratingSupplier);
            txtdistanceSupplier = itemView.findViewById(R.id.txtdistanceSupplier);
            txttimeSupplier = itemView.findViewById(R.id.txttimeSupplier);
            itemSupplierLayout = itemView.findViewById(R.id.itemSupplierLayout);
        }
    }
}