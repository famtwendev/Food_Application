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

        recyclerViewPopular();

        recycleViewsupplier();

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
        category.add(new CategoryModels(9, "Mã giảm giá", R.drawable.cat_9));
        category.add(new CategoryModels(10, "Miễn phí ship", R.drawable.cat_10));
        category.add(new CategoryModels(11, "Khung giờ Sale", R.drawable.cat_11));
        category.add(new CategoryModels(6, "Bún, Phở", R.drawable.cat_6));
        category.add(new CategoryModels(7, "Trà sữa", R.drawable.cat_7));
        category.add(new CategoryModels(8, "Cơm", R.drawable.cat_8));
        category.add(new CategoryModels(1, "Pizza", R.drawable.cat_1));
        category.add(new CategoryModels(2, "HamBurger", R.drawable.cat_2));
        category.add(new CategoryModels(3, "Bánh mì", R.drawable.cat_3));
        category.add(new CategoryModels(4, "Nước ngọt", R.drawable.cat_4));
        category.add(new CategoryModels(5, "Bánh ngọt", R.drawable.cat_5));

        adapterRycycleview = new CategoryAdaptor(category);
        binding.recCategories.setAdapter(adapterRycycleview);
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.recPopular.setLayoutManager(linearLayoutManager);

        ArrayList<FoodModels> fooditem = new ArrayList<>();
        fooditem.add(new FoodModels(1, "Bún Bò", R.drawable.ic_bunbo, "L", "Buổi sáng no nê", 35000, 6, "", "", 3));
        fooditem.add(new FoodModels(2, "Phở Bò", R.drawable.ic_phobo, "L", "Buổi sáng no nê", 35000, 6, "", "", 1));
        fooditem.add(new FoodModels(3, "Cơm tấm", R.drawable.ic_comtam, "L", "Buổi trưa vui vẻ", 32000, 8, "", "", 1));
        fooditem.add(new FoodModels(4, "Bánh canh", R.drawable.ic_banhcanh, "L", "Buổi tối ấm cúng", 35000, 6, "", "", 2));
        fooditem.add(new FoodModels(5, "Trà sữa", R.drawable.ic_trasua, "L", "Tráng miệng", 25000, 7, "", "", 0));
        fooditem.add(new FoodModels(6, "Pizza", R.drawable.ic_pizza, "L", "Tráng miệng", 55000, 1, "", "", 1));
        fooditem.add(new FoodModels(7, "Bún đậu", R.drawable.ic_bundau, "L", "Buổi tối ấm cúng", 75000, 10, "", "", 0));
        fooditem.add(new FoodModels(8, "Bánh ngọt", R.drawable.ic_banhngot, "L", "Tráng miệng", 35000, 5, "", "", 2));
        fooditem.add(new FoodModels(9, "Pepsi", R.drawable.ic_pepsi, "L", "Giải khát", 10000, 4, "", "", 0));
        fooditem.add(new FoodModels(10, "Bánh mì thịt", R.drawable.ic_banhmi, "L", "Bữa sáng no nê", 25000, 3, "", "", 3));


        adapterRycycleview = new PopularAdaptor(fooditem);
        binding.recPopular.setAdapter(adapterRycycleview);
    }


    private void recycleViewsupplier() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recSupplier.setLayoutManager(linearLayoutManager);

        ArrayList<SupplierModels> supplierList = new ArrayList<>();
        ;
        supplierList.add(new SupplierModels("1", "Kichi-Kichi Buffet Lẩu Băng Chuyền", R.drawable.shop_kichikichi, 4, 1.2, 1));
        supplierList.add(new SupplierModels("2", "Dooki LẨU HÀN QUỐC TOKBOKKI BUFFET DOKKI", R.drawable.shop_dokkie, 5, 1.0, 1));
        supplierList.add(new SupplierModels("3", "Hồng trà ngô gia", R.drawable.shop_hongtrangogia, 4, 0.8, 1));
        supplierList.add(new SupplierModels("4", "Lotteria", R.drawable.shop_lotteria, 3, 5.5, 10));
        supplierList.add(new SupplierModels("5", "Hadilao", R.drawable.shop_hadilao, 4, 2.1, 11));
        supplierList.add(new SupplierModels("6", "KFC", R.drawable.food_kfc, 4, 1.8, 2));
        supplierList.add(new SupplierModels("7", "Hủ tiếu Cô Liêu", R.drawable.shop_hutieu, 3, 3.7, 15));
        supplierList.add(new SupplierModels("8", "MC Donal", R.drawable.shop_mcdonal, 4, 3.9, 20));
        supplierList.add(new SupplierModels("9", "Xôi Mặn Hoàng Tử", R.drawable.ic_xoiman, 3, 2.6, 8));

        adapterRycycleview = new SupplierAdapter(supplierList);
        binding.recSupplier.setAdapter(adapterRycycleview);
    }


    private void addEventsForMenu(){
        binding.bottomNavigation.setSelectedItemId(R.id.menu_home);
        updateIcons(0);

        binding.bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                    updateIcons(0);
                } else if (item.getItemId() == R.id.menu_favorite) {
                    Intent intent = new Intent(HomeActivity.this, FavoriteActivity.class);
                    startActivity(intent);
                    updateIcons(1);
                } else if (item.getItemId() == R.id.menu_cart) {
                    Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                    startActivity(intent);
                    updateIcons(2);
                } else if (item.getItemId() == R.id.menu_notify) {
                    Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
                    startActivity(intent);
                    updateIcons(3);
                } else if (item.getItemId() == R.id.menu_customer) {
                    Intent intent = new Intent(HomeActivity.this, CustomerActivity.class);
                    startActivity(intent);
                    updateIcons(4);
                } else {
                    return false; // Return false if no item matches
                }
                return true; // Return true if item was handled
            }
        });
    }
    private void updateIcons(int selectedPosition) {
        MenuItem homeItem = binding.bottomNavigation.getMenu().findItem(R.id.menu_home);
        MenuItem favoriteItem = binding.bottomNavigation.getMenu().findItem(R.id.menu_favorite);
        MenuItem billItem = binding.bottomNavigation.getMenu().findItem(R.id.menu_cart);
        MenuItem notifyItem = binding.bottomNavigation.getMenu().findItem(R.id.menu_notify);
        MenuItem customerItem = binding.bottomNavigation.getMenu().findItem(R.id.menu_customer);

        homeItem.setIcon(selectedPosition == 0 ? R.drawable.ic_house_filled : R.drawable.ic_house);
        favoriteItem.setIcon(selectedPosition == 1 ? R.drawable.ic_heart_filled : R.drawable.ic_heart);
        billItem.setIcon(selectedPosition == 2 ? R.drawable.ic_cart_filled : R.drawable.ic_cart);
        notifyItem.setIcon(selectedPosition == 3 ? R.drawable.ic_notify_filled : R.drawable.ic_notify);
        customerItem.setIcon(selectedPosition == 4 ? R.drawable.ic_user_filled : R.drawable.ic_user);
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