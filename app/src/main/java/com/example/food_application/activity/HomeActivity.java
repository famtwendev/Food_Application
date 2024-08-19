package com.example.food_application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.food_application.R;
import com.example.food_application.adapter.CategoryAdaptor;
import com.example.food_application.databinding.ActivityHomeBinding;
import com.example.food_application.helper.ManagementUser;
import com.example.models.ApiClient;
import com.example.models.ApiService;
import com.example.models.CategoryModels;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    private RecyclerView.Adapter adapterRycycleview;

    private ManagementUser managementUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("HomeActivity", "onCreate");
        managementUser = new ManagementUser(this);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEventsForMenu();

        sliderbarData();

        recyclerViewCategory();

        addEvents();
    }

    private void addEvents() {
        binding.txtmyAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(managementUser.gethasData()){
                    Intent intent = new Intent(HomeActivity.this, InfoActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void loadData() {
        if (managementUser.gethasData()) {
            binding.txtmyAddress.setText(managementUser.getAddress());
        } else {
            binding.txtmyAddress.setText(R.string.my_address);
            Log.e("HomeFragment", "NULL");
        }
    }


    private void sliderbarData() {
        ImageSlider imageSlider = binding.slider;
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.slider1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider5, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
    }


    private void recyclerViewCategory() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        binding.recCategories.setLayoutManager(gridLayoutManager);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getAllCategories().enqueue(new Callback<ArrayList<CategoryModels>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoryModels>> call, Response<ArrayList<CategoryModels>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    ArrayList<CategoryModels> category = response.body();
                    adapterRycycleview = new CategoryAdaptor(category);
                    binding.recCategories.setAdapter(adapterRycycleview);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CategoryModels>> call, Throwable t) {
                Log.e("API ERROR", t.getMessage());
            }
        });
    }


    private void addEventsForMenu() {
        binding.bottomNavigation.setSelectedItemId(R.id.menu_home);
        MenuItem homeItem = binding.bottomNavigation.getMenu().findItem(R.id.menu_home);
        homeItem.setIcon(R.drawable.ic_house_filled);
        binding.bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_favorite) {
                    Intent intent = new Intent(HomeActivity.this, FavoriteActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_cart) {
                    Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_notify) {
                    Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_customer) {
                    Intent intent = new Intent(HomeActivity.this, CustomerActivity.class);
                    startActivity(intent);
                } else {
                    return false; // Return false if no item matches
                }
                return true; // Return true if item was handled
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("HomeActivity", "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("HomeActivity", "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("HomeActivity", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        Log.i("HomeActivity", "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("HomeActivity", "onStop");
    }
}