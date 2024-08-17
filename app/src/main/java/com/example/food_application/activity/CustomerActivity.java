package com.example.food_application.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_application.R;
import com.example.food_application.databinding.ActivityCustomerBinding;
import com.example.food_application.helper.ManagementUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

public class CustomerActivity extends AppCompatActivity {

    ActivityCustomerBinding binding;

    private static final int LOGIN_REQUEST_CODE = 1;
    private ManagementUser managementUser;

    private CalendarView calendarView;
    private TextView tvXuNotification;
    private boolean isXuReceived = false;

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

        //Them o day
        binding.btnshopeecoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(CustomerActivity.this);
                dialog.setContentView(R.layout.activity_shopeexu);
                dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                CalendarView calendarView = dialog.findViewById(R.id.calendarView);
                TextView tvXuNotification = dialog.findViewById(R.id.tvXuNotification);
                TextView tvTotalXu = dialog.findViewById(R.id.tvTotalXu);

                // Retrieve and display current Xu
                int currentXu = 200;
                tvTotalXu.setText("Tổng Xu hiện tại: " + currentXu);

                Calendar currentCalendar = Calendar.getInstance();
                int currentYear = currentCalendar.get(Calendar.YEAR);
                int currentMonth = currentCalendar.get(Calendar.MONTH);
                int currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);

                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);

                        Calendar currentDate = Calendar.getInstance();
                        currentDate.set(currentYear, currentMonth, currentDay);

                        if (selectedDate.before(currentDate)) {
                            Toast.makeText(CustomerActivity.this, "Ngày này đã qua, không thể nhận Xu!", Toast.LENGTH_SHORT).show();
                        } else if (selectedDate.after(currentDate)) {
                            Toast.makeText(CustomerActivity.this, "Chưa đến thời gian nhận Xu!", Toast.LENGTH_SHORT).show();
                        } else if (!isXuReceived) {
                            // Update Xu and display
                            int newXuValue = currentXu + 100;
                            tvTotalXu.setText("Tổng Xu hiện tại: " + newXuValue);
                            tvXuNotification.setText("Bạn đã nhận được 100 Shopee Xu vào ngày " + dayOfMonth + "/" + (month + 1) + "/" + year);
                            Toast.makeText(CustomerActivity.this, "Bạn nhận được 100 Shopee Xu!", Toast.LENGTH_SHORT).show();
                            isXuReceived = true; // Prevent claiming Xu multiple times
                        } else {
                            Toast.makeText(CustomerActivity.this, "Bạn đã nhận Xu cho ngày này rồi!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

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
        String policyContent;
        try {
            //Them o day
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("policy.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while (true) {

                if (!((line = reader.readLine()) != null)) break;

                stringBuilder.append(line).append("\n");
            }
            policyContent = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        binding.btnpolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(CustomerActivity.this);
                dialog.setContentView(R.layout.activity_policy);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setWindowAnimations(R.style.BottomSheetAnimation);
                dialog.getWindow().getAttributes().windowAnimations = R.style.BottomSheetAnimation;

                TextView tvPolicyContent = dialog.findViewById(R.id.tv_policy_content);
                tvPolicyContent.setText(policyContent);

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

        binding.btnsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(CustomerActivity.this);
                dialog.setContentView(R.layout.activity_helper);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setWindowAnimations(R.style.BottomSheetAnimation);
                dialog.getWindow().getAttributes().windowAnimations = R.style.BottomSheetAnimation;
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
        String aboutUsContent;
        try {
            //Them o day
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("aboutUsContent.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while (true) {

                if (!((line = reader.readLine()) != null)) break;

                stringBuilder.append(line).append("\n");
            }
            aboutUsContent = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        binding.btnaboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(CustomerActivity.this);
                dialog.setContentView(R.layout.activity_aboutme);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setWindowAnimations(R.style.BottomSheetAnimation);
                dialog.getWindow().getAttributes().windowAnimations = R.style.BottomSheetAnimation;
                TextView tvAboutmeContent = dialog.findViewById(R.id.tv_aboutme_content);
                tvAboutmeContent.setText(aboutUsContent);

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

            int drawableResourceId = binding.getRoot().getContext().getResources().getIdentifier(managementUser.getPicture(), "drawable", binding.getRoot().getContext().getPackageName());

            binding.imvUser.setImageResource(drawableResourceId);
        } else {
            Log.e("CustomerFragment", "hasData : NULL");
        }
        binding.getRoot().requestLayout();
    }

    private void addEventsForMenu() {
        binding.bottomNavigation.setSelectedItemId(R.id.menu_customer);
        MenuItem customerItem = binding.bottomNavigation.getMenu().findItem(R.id.menu_customer);
        customerItem.setIcon(R.drawable.ic_user_filled);


        binding.bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(CustomerActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_favorite) {
                    Intent intent = new Intent(CustomerActivity.this, FavoriteActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_cart) {
                    Intent intent = new Intent(CustomerActivity.this, CartActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_notify) {
                    Intent intent = new Intent(CustomerActivity.this, NotificationActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_customer) {
                    return true;
                } else {
                    return false; // Return false if no item matches
                }
                return true; // Return true if item was handled
            }
        });
    }
}