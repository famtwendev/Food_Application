package com.example.food_application.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_application.R;
import com.example.food_application.adapter.CartListAdapter;
import com.example.food_application.databinding.ActivityCartBinding;
import com.example.food_application.databinding.CheckoutBottomSheetBinding;
import com.example.food_application.helper.ManagementCart;
import com.example.food_application.helper.ManagementUser;
import com.example.food_application.helper.Utils;
import com.example.food_application.interfaces.ChangeNumberItemListener;
import com.example.food_application.interfaces.ClearAllItem;
import com.example.models.ApiClient;
import com.example.models.ApiService;
import com.example.models.BillDetailModels;
import com.example.models.BillModels;
import com.example.models.FoodModels;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    private ActivityCartBinding binding;

    private CartListAdapter cartListAdapter;

    private RecyclerView.Adapter adapter, adapterCheckout;

    private ManagementCart managementCart;

    private ManagementUser managementUser;

    private String selectedPayment = ""; // Check Method Payment

    private boolean isSuccess = false;
    private int sizeCart = 0; // Check size Cart add success ?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        managementUser = new ManagementUser(this);

        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEventsForMenu();

        managementCart = new ManagementCart(this);

        initList();

        CaculatorCart();

        addEvents();
    }

    private void addEvents() {
        binding.btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (managementUser.gethasData()) {
                    Dialog dialog = new Dialog(CartActivity.this);

                    CheckoutBottomSheetBinding layoutBinding = CheckoutBottomSheetBinding.inflate(getLayoutInflater());

                    // Gán root view của binding cho dialog
                    dialog.setContentView(layoutBinding.getRoot());

                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                dialog.getWindow().setGravity(Gravity.BOTTOM);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().setWindowAnimations(R.style.BottomSheetAnimation);
                    dialog.getWindow().getAttributes().windowAnimations = R.style.BottomSheetAnimation;
                    dialog.show();

                    layoutBinding.btnBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    // Initialize RecyclerView with Adapter
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(dialog.getContext(), LinearLayoutManager.VERTICAL, false);
                    layoutBinding.txtAddress.setText(managementUser.getAddress());
                    layoutBinding.txtNameCustomer.setText(managementUser.getFullname());
                    layoutBinding.txtNumberPhone.setText(managementUser.getNumberPhone());

                    layoutBinding.recyclerViewList.setLayoutManager(linearLayoutManager);

                    layoutBinding.txtItemTotal.setText(binding.txtItemTotal.getText());
                    layoutBinding.txtVAT.setText(binding.txtVAT.getText());
                    layoutBinding.txtDeliveyService.setText(binding.txtDeliveyService.getText());
                    layoutBinding.txtTong.setText(binding.txtTotal.getText());
                    layoutBinding.txtTotal.setText(binding.txtTotal.getText());

                    layoutBinding.radioCOD.setChecked(true);

                    layoutBinding.radioGroup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int checkedId = layoutBinding.radioGroup.getCheckedRadioButtonId();
                            if (checkedId > 0) {
                                if (checkedId == R.id.radioMomo) {
                                    selectedPayment = "Momo";
                                } else if (checkedId == R.id.radioZaloPay) {
                                    selectedPayment = "ZaloPay";
                                } else if (checkedId == R.id.radioCOD) {
                                    selectedPayment = "COD";
                                }
                            }

                            // Xử lý giá trị đã chọn (Ví dụ: lưu vào biến hoặc hiển thị thông báo)
                            Log.d("SelectedPayment", selectedPayment);
                        }
                    });


                    adapterCheckout = new CartListAdapter(managementCart.getListCart(), dialog.getContext(), new ChangeNumberItemListener() {
                        @Override
                        public void change() {
                            DecimalFormat decimalFormat = new DecimalFormat("#,###");
                            double vat;

                            double percentVat = 0.05;

                            double delivery = 30000;

                            vat = Math.round((managementCart.getTotalPrice() * percentVat) * 100) / 100;
                            double total = Math.round((managementCart.getTotalPrice() + vat + delivery) * 100) / 100;
                            double itemTotal = Math.round(managementCart.getTotalPrice() * 100) / 100;

                            layoutBinding.txtItemTotal.setText(decimalFormat.format(itemTotal) + " đ");
                            layoutBinding.txtVAT.setText(decimalFormat.format(vat) + " đ");
                            layoutBinding.txtDeliveyService.setText(decimalFormat.format(delivery) + " đ");

                            layoutBinding.txtTong.setText(decimalFormat.format(total) + " đ");
                            layoutBinding.txtTotal.setText(decimalFormat.format(total) + " đ");
                        }
                    });
                    layoutBinding.recyclerViewList.setAdapter(adapterCheckout);

                    layoutBinding.btnThanhToan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ApiService apiService = ApiClient.getClient().create(ApiService.class);
                            String idBill = managementUser.getIdCustomer() + Utils.generateCustomerCode();
                            BillModels newBill = new BillModels(idBill, managementUser.getIdCustomer(), Utils.addDate(0), Utils.addDate(15), Utils.addDate(30), managementUser.getFullname(), managementUser.getAddress(), managementUser.getEmail(), managementUser.getNumberPhone(), selectedPayment, managementCart.getTotalPrice(), managementCart.getTotalPrice(), "J&T Express", 30000, "1", "Ghi chú", "12345");
                            apiService.addBill(newBill).enqueue(new Callback<BillModels>() {
                                @Override
                                public void onResponse(Call<BillModels> call, Response<BillModels> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        isSuccess = true;
                                        Log.e("API ERROR", "Create BillModels Success");
                                        if (isSuccess) {
                                            Log.e("mess", String.valueOf(isSuccess));
                                            // Lấy danh sách sản phẩm trong giỏ hàng và thêm chi tiết hóa đơn
                                            ArrayList<FoodModels> listFoodInCart = managementCart.getListCart();
                                            ApiService apiServiced = ApiClient.getClient().create(ApiService.class);
                                            for (FoodModels item : listFoodInCart) {
                                                BillDetailModels detail = new BillDetailModels(newBill.getIdBill(), item.getIdFood(), item.getNumberInCart());
                                                apiServiced.addDetails(detail).enqueue(new Callback<BillDetailModels>() {
                                                    @Override
                                                    public void onResponse(Call<BillDetailModels> call, Response<BillDetailModels> detailResponse) {
                                                        if (detailResponse.isSuccessful() && detailResponse.body() != null) {
                                                            sizeCart += 1;
                                                            if (sizeCart == managementCart.getListCart().size()) {
                                                                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(dialog.getContext());
                                                                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                                                                        .inflate(R.layout.dialog_bottom_sheet, null);

                                                                // Ánh xạ nút "OK" và xử lý sự kiện khi nhấn vào
                                                                Button btnOk = bottomSheetView.findViewById(R.id.btn_ok);
                                                                btnOk.setOnClickListener(v -> {
                                                                    // Đóng dialog khi nhấn OK
                                                                    binding.btnDeleteAll.performClick();
                                                                    bottomSheetDialog.dismiss();
                                                                    dialog.dismiss();
                                                                });

                                                                // Thiết lập view cho BottomSheetDialog
                                                                bottomSheetDialog.setContentView(bottomSheetView);
                                                                bottomSheetDialog.setCanceledOnTouchOutside(false);
                                                                bottomSheetDialog.show();
                                                            }
                                                        } else {
                                                            Log.e("API ERROR", "Thêm chi tiết hóa đơn thất bại: " + detailResponse.message());
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<BillDetailModels> call, Throwable t) {
                                                        Log.e("API ERROR", "Lỗi khi gọi API thêm chi tiết hóa đơn: " + t.getMessage());
                                                    }
                                                });
                                            }
                                        } else {
                                            Log.e("API ERROR", "idBill nhận được từ server là null");
                                        }
                                    } else {
                                        Log.e("API ERROR", "Create BillModels Failed: " + response.message());
                                    }
                                }

                                @Override
                                public void onFailure(Call<BillModels> call, Throwable t) {
                                    Log.e("API ERROR", "Lỗi khi gọi API tạo hóa đơn: " + t.getMessage());
                                }
                            });
                        }
                    });
                } else {
                    Intent intent = new Intent(CartActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void change() {
                CaculatorCart();
            }
        });

        binding.recyclerViewList.setAdapter(adapter);


        if (managementCart.getListCart().isEmpty()) {
            binding.emptyCart.setVisibility(View.VISIBLE);
            binding.btnDeleteAll.setVisibility(View.GONE);
            binding.hasDataCart.setVisibility(View.GONE);
            binding.hasDataCart2.setVisibility(View.GONE);
        } else {
            binding.btnDeleteAll.setText("Xóa tất cả (" + adapter.getItemCount() + ")");
            binding.emptyCart.setVisibility(View.GONE);
            binding.btnDeleteAll.setVisibility(View.VISIBLE);
            binding.hasDataCart.setVisibility(View.VISIBLE);
            binding.hasDataCart2.setVisibility(View.VISIBLE);
            binding.btnDeleteAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (adapter instanceof CartListAdapter) {
                        cartListAdapter = (CartListAdapter) adapter;
                        cartListAdapter.clearYourCart();
                        binding.emptyCart.setVisibility(View.VISIBLE);
                        binding.btnDeleteAll.setVisibility(View.GONE);
                        binding.hasDataCart.setVisibility(View.GONE);
                        binding.hasDataCart2.setVisibility(View.GONE);
                        binding.getRoot().requestLayout();
                    } else {
                        Log.e("CartActivity", "Adapter is not an instance of CartListAdapter");
                    }
                }
            });
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

        binding.txtItemTotal.setText(decimalFormat.format(itemTotal) + " đ");
        binding.txtVAT.setText(decimalFormat.format(vat) + " đ");
        binding.txtDeliveyService.setText(decimalFormat.format(delivery) + " đ");

        binding.txtTotal.setText(decimalFormat.format(total) + " đ");
    }

    private void addEventsForMenu() {
        binding.bottomNavigation.setSelectedItemId(R.id.menu_cart);
        MenuItem billItem = binding.bottomNavigation.getMenu().findItem(R.id.menu_cart);
        billItem.setIcon(R.drawable.ic_cart_filled);


        binding.bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_favorite) {
                    Intent intent = new Intent(CartActivity.this, FavoriteActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_cart) {
                    Intent intent = new Intent(CartActivity.this, CartActivity.class);
                    return true;
                } else if (item.getItemId() == R.id.menu_notify) {
                    Intent intent = new Intent(CartActivity.this, NotificationActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.menu_customer) {
                    Intent intent = new Intent(CartActivity.this, CustomerActivity.class);
                    startActivity(intent);
                } else {
                    return false; // Return false if no item matches
                }
                return true; // Return true if item was handled
            }
        });
    }
}