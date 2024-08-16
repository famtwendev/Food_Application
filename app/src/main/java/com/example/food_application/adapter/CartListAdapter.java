package com.example.food_application.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_application.databinding.ViewholderCartBinding;
import com.example.food_application.helper.ManagementCart;
import com.example.food_application.interfaces.ChangeNumberItemListener;
import com.example.food_application.interfaces.ClearAllItem;
import com.example.models.FoodModels;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    ViewholderCartBinding binding;

    private ArrayList<FoodModels> foodModels;
    private ManagementCart managementCart;
    private ChangeNumberItemListener changeNumberItemListener;
    private ClearAllItem clearAllItem;

    public CartListAdapter(ArrayList<FoodModels> foodModels, Context context, ChangeNumberItemListener changeNumberItemListener) {
        this.foodModels = foodModels;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }

    public CartListAdapter(ArrayList<FoodModels> foodModels, Context context, ClearAllItem clearAllItem) {
        this.foodModels = foodModels;
        this.managementCart = new ManagementCart(context);
        this.clearAllItem = clearAllItem;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
//        return new ViewHolder(inflate);
        ViewholderCartBinding binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        holder.binding.nameItem.setText(foodModels.get(position).getNameFood());
        holder.binding.txtPriceItem.setText(decimalFormat.format(foodModels.get(position).getPriceFood()));
        holder.binding.txtTotal.setText(decimalFormat.format(Math.round((foodModels.get(position).getNumberInCart() * foodModels.get(position).getPriceFood()) * 100) / 100));
        holder.binding.txtNumberItem.setText(String.valueOf(foodModels.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodModels.get(position).getPictureFood(), "drawable", holder.itemView.getContext().getPackageName());

        holder.binding.imvItem.setImageResource(drawableResourceId);

        int thisposition = holder.getBindingAdapterPosition();
//        if (getposition == RecyclerView.NO_POSITION) { // Kiểm tra nếu vị trí là hợp lệ
//        }

        holder.binding.btnPlusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.plusNumberFood(foodModels, thisposition, new ChangeNumberItemListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemListener.change();
                    }
                });
            }
        });

        holder.binding.btnMinusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumbeFood(foodModels, thisposition, new ChangeNumberItemListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemListener.change();
                    }
                });
            }
        });

        holder.binding.btnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.removeItem(foodModels, thisposition, new ChangeNumberItemListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemListener.change();
                        if(foodModels.isEmpty())
                        {
                            ((Activity) view.getContext()).recreate();
                        }
                    }
                });
            }
        });
    }
    @Override
    public int getItemCount() {
        return foodModels.size();
    }


    public void clearYourCart() {
        managementCart.clearAllItems(foodModels, new ClearAllItem() {
            @Override
            public void clearAll() {
                foodModels.clear();
                notifyDataSetChanged();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Khai báo ViewBinding
        private final ViewholderCartBinding binding;

        public ViewHolder(ViewholderCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }
}
