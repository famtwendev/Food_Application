package com.example.food_application;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.adapter.ViewPageMainFragmentAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.food_application.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewPageMainFragmentAdapter adapter = new ViewPageMainFragmentAdapter(this);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setOffscreenPageLimit(2);

        // Disable swipe
        binding.viewPager.setUserInputEnabled(false);
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
            // Update BottomNavigationView based on ViewPager page
            @Override
            public void onPageSelected(int position) {
                updateIcons(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        binding.bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    binding.viewPager.setCurrentItem(0);
                    updateIcons(0);
                } else if (item.getItemId() == R.id.menu_cart) {
                    binding.viewPager.setCurrentItem(1);
                    updateIcons(1);
                } else if (item.getItemId() == R.id.menu_favorite) {
                    binding.viewPager.setCurrentItem(2);
                    updateIcons(2);
                } else if (item.getItemId() == R.id.menu_notify) {
                    binding.viewPager.setCurrentItem(3);
                    updateIcons(3);
                } else if (item.getItemId() == R.id.menu_customer) {
                    binding.viewPager.setCurrentItem(4);
                    updateIcons(4);
                } else {
                    return false; // Return false if no item matches
                }
                return true; // Return true if item was handled
            }
        });


        Log.i("MainActivity", "onCreate");
    }
    private void updateIcons(int selectedPosition) {
        MenuItem homeItem = binding.bottomNavigation.getMenu().findItem(R.id.menu_home);
        MenuItem billItem = binding.bottomNavigation.getMenu().findItem(R.id.menu_cart);
        MenuItem favoriteItem = binding.bottomNavigation.getMenu().findItem(R.id.menu_favorite);
        MenuItem notifyItem = binding.bottomNavigation.getMenu().findItem(R.id.menu_notify);
        MenuItem customerItem = binding.bottomNavigation.getMenu().findItem(R.id.menu_customer);

        homeItem.setIcon(selectedPosition == 0 ? R.drawable.ic_house_filled : R.drawable.ic_house);
        billItem.setIcon(selectedPosition == 1 ? R.drawable.ic_cart_filled : R.drawable.ic_cart);
        favoriteItem.setIcon(selectedPosition == 2 ? R.drawable.ic_heart_filled : R.drawable.ic_heart);
        notifyItem.setIcon(selectedPosition == 3 ? R.drawable.ic_notify_filled : R.drawable.ic_notify);
        customerItem.setIcon(selectedPosition == 4 ? R.drawable.ic_user_filled : R.drawable.ic_user);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop");
    }
}
