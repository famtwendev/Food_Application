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
import com.example.food_application.adapter.SupplierAdapter;
import com.example.food_application.databinding.ActivityShopFoodBinding;
import com.example.food_application.helper.Utils;
import com.example.models.SupplierModels;

import java.util.ArrayList;

public class ShopFoodActivity extends AppCompatActivity {

    ActivityShopFoodBinding binding;

    ArrayList<SupplierModels> supplierList;

    private RecyclerView.Adapter adapterRycycleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShopFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recycleViewsupplier();

        getData();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void getData() {
        ArrayList<SupplierModels> temp = new ArrayList<>();
        Intent intent = getIntent();
        int category = intent.getIntExtra(Utils.CATEGORY,-1);
        if (category != -1) {
            for (SupplierModels item : supplierList
            ) {
                if (item.getIdCategory() == category) {
                    temp.add(item);
                }
            }
        }
        adapterRycycleview = new SupplierAdapter(temp);
        binding.recSupplier.setAdapter(adapterRycycleview);
    }

    private void recycleViewsupplier() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recSupplier.setLayoutManager(linearLayoutManager);

        supplierList = new ArrayList<>();

        supplierList.add(new SupplierModels("1", "Kichi-Kichi Buffet Lẩu Băng Chuyền", "shop_kichikichi", 4, "Thủ đức", 1.2, 1, 1));
        supplierList.add(new SupplierModels("2", "Dooki LẨU HÀN QUỐC TOKBOKKI BUFFET DOKKI", "shop_dokkie", 5, "Thủ đức", 1.0, 1, 2));
        supplierList.add(new SupplierModels("3", "Hồng trà ngô gia", "shop_hongtrangogia", 4, "Thủ đức", 0.8, 1, 3));
        supplierList.add(new SupplierModels("4", "Lotteria", "shop_lotteria", 3, "Thủ đức", 5.5, 10, 4));
        supplierList.add(new SupplierModels("5", "Hadilao", "shop_hadilao", 4, "Thủ đức", 2.1, 11, 5));
        supplierList.add(new SupplierModels("6", "KFC", "food_kfc", 4, "Thủ đức", 1.8, 2, 6));
        supplierList.add(new SupplierModels("7", "Hủ tiếu Cô Liêu", "shop_hutieu", 3, "Thủ đức", 1.2, 3, 7));
        supplierList.add(new SupplierModels("8", "MC Donal", "shop_mcdonal", 4, "Thủ đức", 3.9, 20, 8));
        supplierList.add(new SupplierModels("9", "Xôi Mặn Hoàng Tử", "ic_xoiman", 3, "Thủ đức", 2.6, 8, 9));
    }
}