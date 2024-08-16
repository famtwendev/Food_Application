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
import com.example.models.CategoryModels;
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

        addEvents();

    }

    private void addEvents() {
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
        Bundle bundle = intent.getBundleExtra(Utils.CATEGORY);
        assert bundle != null;

        CategoryModels categoryInfo = (CategoryModels) bundle.getSerializable(Utils.THISCATEGORY);
        assert categoryInfo != null;
        for (SupplierModels item : supplierList
        ) {
            if (item.getIdCategory() == categoryInfo.getIdCategory()) {
                temp.add(item);
            }
        }
        binding.lbCategory.setText(categoryInfo.getTitleCategory());


        adapterRycycleview = new SupplierAdapter(temp);
        binding.recSupplier.setAdapter(adapterRycycleview);
    }

    private void recycleViewsupplier() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recSupplier.setLayoutManager(linearLayoutManager);

        supplierList = new ArrayList<>();

        supplierList.add(new SupplierModels("1", "Kichi-Kichi Buffet Lẩu Băng Chuyền", "shop_kichikichi", 4.1, 123, "Thủ đức", 1.2, 1, 1));
        supplierList.add(new SupplierModels("2", "Dooki LẨU HÀN QUỐC TOKBOKKI BUFFET DOKKI", "shop_dokkie", 4.2, 11, "Thủ đức", 1.0, 1, 2));
        supplierList.add(new SupplierModels("3", "Hồng trà ngô gia", "shop_hongtrangogia", 5.0, 102, "Thủ đức", 0.8, 1, 3));
        supplierList.add(new SupplierModels("4", "Lotteria", "shop_lotteria", 3.2, 78, "Thủ đức", 5.5, 10, 4));
        supplierList.add(new SupplierModels("5", "Hadilao", "shop_hadilao", 4.8, 73, "Thủ đức", 2.1, 11, 5));
        supplierList.add(new SupplierModels("6", "KFC", "food_kfc", 4.9, 67, "Thủ đức", 1.8, 2, 6));
        supplierList.add(new SupplierModels("7", "Hủ tiếu Cô Liêu", "shop_hutieu", 3.7, 999, "Thủ đức", 1.2, 3, 7));
        supplierList.add(new SupplierModels("8", "MC Donal", "shop_mcdonal", 4.1, 191, "Thủ đức", 3.9, 20, 8));
        supplierList.add(new SupplierModels("9", "Xôi Mặn Hoàng Tử", "ic_xoiman", 3.9, 382, "Thủ đức", 2.6, 8, 9));
    }
}