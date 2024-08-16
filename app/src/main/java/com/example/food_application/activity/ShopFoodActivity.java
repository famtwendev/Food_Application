package com.example.food_application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.food_application.databinding.ActivityShopFoodBinding;
import com.example.food_application.helper.Utils;
import com.example.models.ApiClient;
import com.example.models.ApiService;
import com.example.models.CategoryModels;
import com.example.models.FoodModels;
import com.example.models.SupplierModels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopFoodActivity extends AppCompatActivity {

    ActivityShopFoodBinding binding;

    ArrayList<SupplierModels> supplierList;

    private RecyclerView.Adapter adapterRycycleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShopFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        recycleViewsupplier();

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recSupplier.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Utils.CATEGORY);
        assert bundle != null;

        CategoryModels categoryInfo = (CategoryModels) bundle.getSerializable(Utils.THISCATEGORY);
        assert categoryInfo != null;

        binding.lbCategory.setText(categoryInfo.getTitleCategory());

        Log.e("Data CategoryModels", categoryInfo.toString());

        ArrayList<SupplierModels> temp = new ArrayList<>();
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getAllSuppliers().enqueue(new Callback<ArrayList<SupplierModels>>() {
            @Override
            public void onResponse(Call<ArrayList<SupplierModels>> call, Response<ArrayList<SupplierModels>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    ArrayList<SupplierModels> supplierModels = response.body();
                    for (SupplierModels item : supplierModels) {
                        if (item.getIdCategory() == categoryInfo.getIdCategory()) {
                            temp.add(item);
                        }
                    }
                    adapterRycycleview = new SupplierAdapter(temp);
                    binding.recSupplier.setAdapter(adapterRycycleview);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SupplierModels>> call, Throwable t) {
                Log.e("API ERROR", t.getMessage());
            }
        });

    }
}