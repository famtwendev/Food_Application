package com.example.food_application.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.food_application.R;
import com.example.food_application.adapter.ViewPageFavoriteFragmentAdapter;
import com.example.food_application.databinding.ActivityFavoriteBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayoutMediator;

public class FavoriteActivity extends AppCompatActivity {
    ActivityFavoriteBinding binding;
    private ViewPager2 viewPager;
    private ViewPageFavoriteFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the adapter before setting it to ViewPager2
        adapter = new ViewPageFavoriteFragmentAdapter(this);

        addEventsForMenu();

        viewPager = binding.viewPagerFavorite;
        viewPager.setOffscreenPageLimit(2);

        viewPager.setAdapter(adapter);


        new TabLayoutMediator(binding.tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Yêu thích");
                    break;
                case 1:
                    tab.setText("Đang đến");
                    break;
                case 2:
                    tab.setText("Lịch sử");
                    break;
                case 3:
                    tab.setText("Đánh giá");
                    break;
            }
        }).attach();
    }





    private void addEventsForMenu(){
        binding.bottomNavigation.setSelectedItemId(R.id.menu_favorite);
        MenuItem favoriteItem = binding.bottomNavigation.getMenu().findItem(R.id.menu_favorite);
        favoriteItem.setIcon(R.drawable.ic_heart_filled);


        binding.bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(FavoriteActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_favorite) {
                    return true;
                } else if (item.getItemId() == R.id.menu_cart) {
                    Intent intent = new Intent(FavoriteActivity.this, CartActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_notify) {
                    Intent intent = new Intent(FavoriteActivity.this, NotificationActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_customer) {
                    Intent intent = new Intent(FavoriteActivity.this, CustomerActivity.class);
                    startActivity(intent);
                } else {
                    return false; // Return false if no item matches
                }
                return true; // Return true if item was handled
            }
        });
    }
}