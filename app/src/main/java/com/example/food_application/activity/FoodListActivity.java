package com.example.food_application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_application.R;
import com.example.food_application.adapter.PopularAdaptor;
import com.example.food_application.adapter.SupplierAdapter;
import com.example.food_application.databinding.ActivityFoodListBinding;
import com.example.food_application.helper.Utils;
import com.example.models.CategoryModels;
import com.example.models.FoodModels;
import com.example.models.SupplierModels;

import java.util.ArrayList;

public class FoodListActivity extends AppCompatActivity {

    ActivityFoodListBinding binding;
    private RecyclerView.Adapter adapterRycycleview;
    private ArrayList<FoodModels> foodlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerViewPopular();

        getData();

        addEvents();

    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodListActivity.this, CartActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    private void getData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recPopular.setLayoutManager(linearLayoutManager);

        ArrayList<FoodModels> temp = new ArrayList<>();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Utils.SUPPLIER);
        assert bundle != null;

        SupplierModels supplierInfo = (SupplierModels) bundle.getSerializable(Utils.THISSUPPLIER);
        assert supplierInfo != null;
        for (FoodModels item : foodlist
        ) {
            if (item.getIdSupplier().equals(supplierInfo.getIdSupplier())) {
                temp.add(item);
            }
        }
        binding.txtNameSupplier.setText(supplierInfo.getNameSupplier());
        binding.txttimeSupplier.setText(String.format("%d phút", supplierInfo.getTimeSupplier()));
        binding.txtRatingcount.setText(String.format("(%d+)", supplierInfo.getRatingCount()));
        binding.txtratingSupplier.setText(String.format("%.1f", supplierInfo.getRatingSupplier()));
        int drawableResourceId = binding.getRoot().getContext().getResources().getIdentifier(supplierInfo.getPictureSupplier(), "drawable", binding.getRoot().getContext().getPackageName());
        binding.bgsupplier.setBackgroundResource(drawableResourceId);

        adapterRycycleview = new PopularAdaptor(temp);
        binding.recPopular.setAdapter(adapterRycycleview);
    }

    private void recyclerViewPopular() {
        foodlist = new ArrayList<>();
        foodlist.add(new FoodModels(1, "Bún Bò", "ic_bunbo", "L", "Buổi sáng no nê", 35000, "1", ""));
        foodlist.add(new FoodModels(2, "Phở Bò", "ic_phobo", "L", "Buổi sáng no nê", 35000, "2", ""));
        foodlist.add(new FoodModels(3, "Cơm tấm", "ic_comtam", "L", "Buổi trưa vui vẻ", 32000, "3", ""));
        foodlist.add(new FoodModels(4, "Bánh canh", "ic_banhcanh", "L", "Buổi tối ấm cúng", 35000, "4", ""));
        foodlist.add(new FoodModels(5, "Trà sữa", "ic_trasua", "L", "Tráng miệng", 25000, "5", ""));
        foodlist.add(new FoodModels(6, "Pizza", "ic_pizza", "L", "Tráng miệng", 55000, "6", ""));
        foodlist.add(new FoodModels(7, "Bún đậu", "ic_bundau", "L", "Buổi tối ấm cúng", 75000, "8", ""));
        foodlist.add(new FoodModels(8, "Bánh ngọt", "ic_banhngot", "L", "Tráng miệng", 35000, "8", ""));
        foodlist.add(new FoodModels(9, "Pepsi", "ic_pepsi", "L", "Giải khát", 10000, "9", ""));
        foodlist.add(new FoodModels(10, "Bánh mì thịt", "ic_banhmi", "L", "Bữa sáng no nê", 25000, "1", ""));
    }
}