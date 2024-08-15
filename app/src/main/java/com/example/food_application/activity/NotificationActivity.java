package com.example.food_application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    private void addEventsForMenu() {
        binding.bottomNavigation.setSelectedItemId(R.id.menu_notify);
        MenuItem notifyItem = binding.bottomNavigation.getMenu().findItem(R.id.menu_notify);
        notifyItem.setIcon(R.drawable.ic_notify_filled);


        binding.bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(NotificationActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_favorite) {
                    Intent intent = new Intent(NotificationActivity.this, FavoriteActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_cart) {
                    Intent intent = new Intent(NotificationActivity.this, CartActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_notify) {
                    return true;
                } else if (item.getItemId() == R.id.menu_customer) {
                    Intent intent = new Intent(NotificationActivity.this, CustomerActivity.class);
                    startActivity(intent);
                } else {
                    return false; // Return false if no item matches
                }
                return true; // Return true if item was handled
            }
        });
    }
}