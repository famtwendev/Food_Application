package com.example.food_application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.food_application.R;
import com.example.food_application.databinding.ActivityNotificationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NotificationActivity extends AppCompatActivity {

    ActivityNotificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEventsForMenu();

    }

    private void addEventsForMenu(){
        binding.bottomNavigation.setSelectedItemId(R.id.menu_notify);
        updateIcons(3);

        binding.bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(NotificationActivity.this, HomeActivity.class);
                    startActivity(intent);
                    updateIcons(0);
                } else if (item.getItemId() == R.id.menu_favorite) {
                    Intent intent = new Intent(NotificationActivity.this, FavoriteActivity.class);
                    startActivity(intent);
                    updateIcons(1);
                } else if (item.getItemId() == R.id.menu_cart) {
                    Intent intent = new Intent(NotificationActivity.this, CartActivity.class);
                    startActivity(intent);
                    updateIcons(2);
                } else if (item.getItemId() == R.id.menu_notify) {
                    return true;
                } else if (item.getItemId() == R.id.menu_customer) {
                    Intent intent = new Intent(NotificationActivity.this, CustomerActivity.class);
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
}