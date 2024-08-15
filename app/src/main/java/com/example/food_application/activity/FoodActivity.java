package com.example.food_application.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_application.R;
import com.example.food_application.adapter.PopularAdaptor;
import com.example.food_application.databinding.ActivityFoodBinding;
import com.example.food_application.databinding.FoodFavoriteBinding;
import com.example.models.FoodModels;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {

    ActivityFoodBinding binding;
    private RecyclerView.Adapter adapterRycycleview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerViewPopular();
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recPopular.setLayoutManager(linearLayoutManager);

        ArrayList<FoodModels> fooditem = new ArrayList<>();
        fooditem.add(new FoodModels(1, "Bún Bò", "ic_bunbo", "L", "Buổi sáng no nê", 35000, "1", "", 1, 311));
        fooditem.add(new FoodModels(2, "Phở Bò", "ic_phobo", "L", "Buổi sáng no nê", 35000, "2", "", 2, 211));
        fooditem.add(new FoodModels(3, "Cơm tấm", "ic_comtam", "L", "Buổi trưa vui vẻ", 32000, "3", "", 3, 11));
        fooditem.add(new FoodModels(4, "Bánh canh", "ic_banhcanh", "L", "Buổi tối ấm cúng", 35000, "4", "", 1, 221));
        fooditem.add(new FoodModels(5, "Trà sữa", "ic_trasua", "L", "Tráng miệng", 25000, "5", "", 5, 54));
        fooditem.add(new FoodModels(6, "Pizza", "ic_pizza", "L", "Tráng miệng", 55000, "6", "", 4, 12));
        fooditem.add(new FoodModels(7, "Bún đậu", "ic_bundau", "L", "Buổi tối ấm cúng", 75000, "7", "", 3, 110));
        fooditem.add(new FoodModels(8, "Bánh ngọt", "ic_banhngot", "L", "Tráng miệng", 35000, "8", "", 2, 55));
        fooditem.add(new FoodModels(9, "Pepsi", "ic_pepsi", "L", "Giải khát", 10000, "9", "", 2, 999));
        fooditem.add(new FoodModels(10, "Bánh mì thịt", "ic_banhmi", "L", "Bữa sáng no nê", 25000, "1", "", 1, 932));


        adapterRycycleview = new PopularAdaptor(fooditem);
    }
}