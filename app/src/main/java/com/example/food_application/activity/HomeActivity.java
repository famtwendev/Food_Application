package com.example.food_application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.food_application.R;
import com.example.food_application.adapter.CategoryAdaptor;
import com.example.food_application.adapter.PopularAdaptor;
import com.example.food_application.adapter.SupplierAdapter;
import com.example.food_application.databinding.ActivityHomeBinding;
import com.example.food_application.helper.ManagementUser;
import com.example.models.CategoryModels;
import com.example.models.FoodModels;
import com.example.models.SupplierModels;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

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

        recyclerViewCatrgory();

        addEvents();
    }

    private void addEvents() {
        binding.txtmyAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Thay đổi địa chỉ", Toast.LENGTH_SHORT).show();
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



    private void recyclerViewCatrgory() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        binding.recCategories.setLayoutManager(gridLayoutManager);

        ArrayList<CategoryModels> category = new ArrayList<>();
        category.add(new CategoryModels(9, "Mã giảm giá", "cat_9"));
        category.add(new CategoryModels(10, "Miễn phí ship", "cat_10"));
        category.add(new CategoryModels(11, "Khung giờ Sale", "cat_11"));
        category.add(new CategoryModels(6, "Bún, Phở", "cat_6"));
        category.add(new CategoryModels(7, "Trà sữa", "cat_7"));
        category.add(new CategoryModels(8, "Cơm", "cat_8"));
        category.add(new CategoryModels(1, "Pizza", "cat_1"));
        category.add(new CategoryModels(2, "HamBurger", "cat_2"));
        category.add(new CategoryModels(3, "Bánh mì", "cat_3"));
        category.add(new CategoryModels(4, "Nước ngọt", "cat_4"));
        category.add(new CategoryModels(5, "Bánh ngọt", "cat_5"));

        adapterRycycleview = new CategoryAdaptor(category);
        binding.recCategories.setAdapter(adapterRycycleview);
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