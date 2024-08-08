package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_application.R;
import com.example.models.CategoryModels;

import java.util.ArrayList;


public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder> {

    ArrayList<CategoryModels> categoryDomains;

    public CategoryAdaptor(ArrayList<CategoryModels> categoryDomains) {
        this.categoryDomains = categoryDomains;
    }

    @Override
    public CategoryAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(categoryDomains.get(position).getTitleCategory());

        holder.itemCategoryLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_cat));

        holder.categoryPic.setImageResource(categoryDomains.get(position).getPicCategory());
    }

    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout itemCategoryLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.imvCategoryPic);
            itemCategoryLayout = itemView.findViewById(R.id.itemCategoryLayout);
        }
    }
}
