
package com.example.food_application.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_application.R;
import com.example.food_application.activity.HomeActivity;
import com.example.food_application.activity.ShopFoodActivity;
import com.example.food_application.helper.Utils;
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

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(categoryDomains.get(position).getPicCategory(), "drawable", holder.itemView.getContext().getPackageName());


        holder.categoryPic.setImageResource(drawableResourceId);

        int thisposition = holder.getBindingAdapterPosition();

        holder.itemCategoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShopFoodActivity.class);
                Bundle bundle = new Bundle();

                CategoryModels categoryInfo = new CategoryModels(categoryDomains.get(position).getIdCategory(),categoryDomains.get(position).getTitleCategory(),categoryDomains.get(position).getPicCategory());

                bundle.putSerializable(Utils.THISCATEGORY, categoryInfo);

                intent.putExtra(Utils.CATEGORY, bundle);
                view.getContext().startActivity(intent);
            }
        });
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
