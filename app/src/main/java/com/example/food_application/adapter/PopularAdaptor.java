package com.example.food_application.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_application.R;
import com.example.food_application.activity.DetailFoodActivity;
import com.example.models.FoodModels;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ViewHolder> {

    ArrayList<FoodModels> foodlist;

    public PopularAdaptor(ArrayList<FoodModels> foodModels) {
        this.foodlist = foodModels;
    }

    @Override
    public PopularAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_food, parent, false);
        return new ViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
        String formattedPrice = decimalFormat.format(foodlist.get(position).getPriceFood());
        //String.format("%.0f đ", foodlist.get(position).getPriceFood())
        holder.txtNameFood.setText(foodlist.get(position).getNameFood());
        holder.txtPriceFood.setText(formattedPrice);
        holder.itemProductLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_cart));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodlist.get(position).getPictureFood(), "drawable", holder.itemView.getContext().getPackageName());
        holder.imvPicFood.setImageResource(drawableResourceId);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newposition = holder.getAdapterPosition();
                Intent intent = new Intent(holder.itemView.getContext(), DetailFoodActivity.class);
                intent.putExtra("object", foodlist.get(newposition));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameFood, txtPriceFood;
        ImageView imvPicFood, addBtn;
        LinearLayout itemProductLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameFood = itemView.findViewById(R.id.txtNameFood);
            txtPriceFood = itemView.findViewById(R.id.txtPriceFood);
            imvPicFood = itemView.findViewById(R.id.imvPhotoFood);
            itemProductLayout = itemView.findViewById(R.id.itemProductLayout);
            addBtn = itemView.findViewById(R.id.btnAdd);
        }
    }
}