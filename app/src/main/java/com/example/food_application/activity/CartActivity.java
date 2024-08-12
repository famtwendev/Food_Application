package com.example.food_application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_application.R;
import com.example.food_application.adapter.CartListAdapter;
import com.example.food_application.databinding.ActivityCartBinding;
import com.example.food_application.helper.ManagementCart;
import com.example.food_application.interfaces.ChangeNumberItemListener;
import com.example.models.FoodModels;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ActivityCartBinding binding;

    private CartListAdapter cartListAdapter;

    private RecyclerView.Adapter adapter;
    private ManagementCart managementCart;


    // Xử lý sự kiện nhấn nút "Xóa tất cả"

    private ArrayList<FoodModels> foodModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEventsForMenu();

        managementCart = new ManagementCart(this);

        initList();

        CaculatorCart();


    }


    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(),this, new ChangeNumberItemListener() {
            @Override
            public void change() {
                CaculatorCart();
            }
        });

        binding.recyclerViewList.setAdapter(adapter);


        if(managementCart.getListCart().isEmpty())
        {
            binding.emptyCart.setVisibility(View.VISIBLE);
            binding.btnDeleteAll.setVisibility(View.GONE);
            binding.hasDataCart.setVisibility(View.GONE);
            binding.hasDataCart2.setVisibility(View.GONE);
        }else {
            binding.btnDeleteAll.setText("Xóa tất cả ("+adapter.getItemCount()+")");
            binding.emptyCart.setVisibility(View.GONE);
            binding.btnDeleteAll.setVisibility(View.VISIBLE);
            binding.hasDataCart.setVisibility(View.VISIBLE);
            binding.hasDataCart2.setVisibility(View.VISIBLE);

        }
    }

    private void CaculatorCart() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        double vat;

        double percentVat = 0.05;

        double delivery = 30000;

        vat = Math.round((managementCart.getTotalPrice() * percentVat) * 100) / 100;
        double total = Math.round((managementCart.getTotalPrice() + vat + delivery) * 100) / 100;
        double itemTotal = Math.round(managementCart.getTotalPrice() * 100) / 100;

        binding.txtItemTotal.setText(decimalFormat.format(itemTotal)+ " đ");
        binding.txtVAT.setText(decimalFormat.format(vat) +" đ");
        binding.txtDeliveyService.setText(decimalFormat.format(delivery) +" đ");

        binding.txtTotal.setText(decimalFormat.format(total) + " đ");
    }

    private void addEventsForMenu() {
        binding.bottomNavigation.setSelectedItemId(R.id.menu_cart);
        updateIcons(2);


        binding.bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                    startActivity(intent);
                    updateIcons(0);
                } else if (item.getItemId() == R.id.menu_favorite) {
                    Intent intent = new Intent(CartActivity.this, FavoriteActivity.class);
                    startActivity(intent);
                    updateIcons(1);
                } else if (item.getItemId() == R.id.menu_cart) {
                    Intent intent = new Intent(CartActivity.this, CartActivity.class);
                    return true;
                } else if (item.getItemId() == R.id.menu_notify) {
                    Intent intent = new Intent(CartActivity.this, NotificationActivity.class);
                    startActivity(intent);
                    updateIcons(3);
                } else if (item.getItemId() == R.id.menu_customer) {
                    Intent intent = new Intent(CartActivity.this, CustomerActivity.class);
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