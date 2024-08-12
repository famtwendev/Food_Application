package com.example.food_application.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_application.R;
import com.example.food_application.databinding.ActivityCustomerBinding;
import com.example.food_application.helper.ManagementUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerActivity extends AppCompatActivity {

    ActivityCustomerBinding binding;

    private static final int LOGIN_REQUEST_CODE = 1;
    private ManagementUser managementUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEventsForMenu();
        managementUser = new ManagementUser(this);
        addEvents();

    }

    private void addEvents() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xóa thông tin người dùng
                managementUser.destroy();
                // Cập nhật giao diện
                binding.btnLogin.setVisibility(View.VISIBLE);
                binding.txtusername.setVisibility(View.GONE);
                binding.imvUser.setImageResource(R.drawable.ic_customer);
                binding.btnLogout.setVisibility(View.GONE);
            }
        });

        binding.btnvoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (managementUser == null) {
                    binding.btnLogin.performClick();
                }
            }
        });
        binding.btnshopeecoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (managementUser == null) {
                    binding.btnLogin.performClick();
                }
            }
        });

        binding.btnmyaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (managementUser == null) {
                    binding.btnLogin.performClick();
                }
            }
        });
        binding.btnpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (managementUser == null) {
                    binding.btnLogin.performClick();
                }
            }
        });

        binding.btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (managementUser == null) {
                    binding.btnLogin.performClick();
                }
            }
        });

        binding.btnsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(CustomerActivity.this);
                dialog.setContentView(R.layout.activity_helper);
                dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.findViewById(R.id.btnbacklogin).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
        binding.btnaboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (managementUser == null) {
                    binding.btnLogin.performClick();
                }
            }
        });
    }
    @Override
    public void onResume() {
        Log.e("CustomerFragment", "onResume");
        super.onResume();
        loadData();
    }


    private void loadData() {
        if (managementUser.gethasData()) {
            binding.btnLogin.setVisibility(View.GONE);
            binding.btnLogout.setVisibility(View.VISIBLE);
            binding.txtusername.setVisibility(View.VISIBLE);
            binding.txtusername.setText(managementUser.getFullname());
            binding.imvUser.setImageResource(managementUser.getPicture());
        } else {
            Log.e("CustomerFragment", "hasData : NULL");
        }
        binding.getRoot().requestLayout();
    }

    private void addEventsForMenu(){
        binding.bottomNavigation.setSelectedItemId(R.id.menu_customer);
        updateIcons(4);


        binding.bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(CustomerActivity.this, HomeActivity.class);
                    startActivity(intent);
                    updateIcons(0);
                } else if (item.getItemId() == R.id.menu_favorite) {
                    Intent intent = new Intent(CustomerActivity.this, FavoriteActivity.class);
                    startActivity(intent);
                    updateIcons(1);
                } else if (item.getItemId() == R.id.menu_cart) {
                    Intent intent = new Intent(CustomerActivity.this, CartActivity.class);
                    startActivity(intent);
                    updateIcons(2);
                } else if (item.getItemId() == R.id.menu_notify) {
                    Intent intent = new Intent(CustomerActivity.this, NotificationActivity.class);
                    startActivity(intent);
                    updateIcons(3);
                } else if (item.getItemId() == R.id.menu_customer) {
                    return true;
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