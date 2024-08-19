package com.example.food_application.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.food_application.databinding.ActivityRegistBinding;
import com.example.food_application.databinding.DialogChangepasswordBinding;
import com.example.food_application.helper.ManagementUser;
import com.example.food_application.R;
import com.example.food_application.databinding.ActivityLoginBinding;
import com.example.models.ApiClient;
import com.example.models.ApiService;
import com.example.models.CategoryModels;
import com.example.models.CustomerModels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ManagementUser managementUser;
    ActivityLoginBinding binding;

    private boolean isHidden = true; // Dang an

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        managementUser = new ManagementUser(LoginActivity.this);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvents();

    }

    private void addEvents() {
        binding.btnLoginMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.logindefault.setVisibility(View.GONE); // an
                binding.loginbangmatkhau.setVisibility(View.VISIBLE); // hien
                binding.btnLoginMatKhau.setVisibility(View.GONE); // an


                binding.row2login.requestLayout();
            }
        });

        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.edtUsernameEmail.getText().toString().trim();
                String password = binding.edtPassword.getText().toString().trim();
                // Kiểm tra thông tin đăng nhập
                ApiService apiService = ApiClient.getClient().create(ApiService.class);
                apiService.getAllCustomer().enqueue(new Callback<ArrayList<CustomerModels>>() {
                    @Override
                    public void onResponse(Call<ArrayList<CustomerModels>> call, Response<ArrayList<CustomerModels>> response) {
                        boolean hasAccount = false;
                        if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                            ArrayList<CustomerModels> customerModels = response.body();
                            for (CustomerModels item : customerModels
                            ) {
                                if (item.getUsername().equals(username) && item.getPassword().equals(password) || item.getEmail().equals(username) && item.getPassword().equals(password)) {
                                    hasAccount = true;
                                    CustomerModels account = item;
                                    if (account != null) {
                                        managementUser.saveUserInfo(
                                                account.getIdCustomer(), // idCustomer
                                                account.getPassword(), // password
                                                account.getFullname(), // fullname
                                                account.getUsername(), // username
                                                account.getSex(), // sex
                                                account.getBirthday(), // birthday
                                                account.getAddress(), // address
                                                account.getNumberPhone(), // numberPhone
                                                account.getEmail(), // email
                                                account.getPicture(), // picture
                                                account.getIsValue(), // isValue
                                                account.getScoreRating(), // scoreRating
                                                account.getRandomKey() // randomKey
                                        );
                                        managementUser.setHasData();
                                        finish();
                                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.e("LoginActivity", "account null data");
                                        // Đăng nhập thất bại, thông báo cho người dùng
                                    }
                                    break;
                                }
                            }
                        }
                        if (hasAccount == false) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setTitle("Sai tên người dùng hoặc mật khẩu!");

                            Drawable icon = getResources().getDrawable(android.R.drawable.ic_dialog_alert);
                            icon.setColorFilter(ContextCompat.getColor(LoginActivity.this, R.color.yellow), PorterDuff.Mode.SRC_IN);
                            builder.setIcon(icon);
                            builder.setMessage("Vui lòng đăng nhập lại?");

                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });

                            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                }
                            });

                            Dialog dialog = builder.create();
                            dialog.setCanceledOnTouchOutside(false);
                            dialog.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<CustomerModels>> call, Throwable t) {
                        Log.e("API ERROR", t.getMessage());
                    }
                });
            }

            private void saveInfoUser(CustomerModels item) {

            }
        });
        binding.icHiddenpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isHidden) {
                    binding.edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    binding.icHiddenpass.setImageResource(R.drawable.ic_eyes);
                } else {
                    binding.edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.icHiddenpass.setImageResource(R.drawable.ic_eyes_hidden);
                }
                isHidden = !isHidden; // Cập nhật trạng thái sau mỗi lần nhấp
            }
        });
        binding.btnbacklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(LoginActivity.this);

                ActivityRegistBinding layoutBinding = ActivityRegistBinding.inflate(getLayoutInflater());
                dialog.setContentView(layoutBinding.getRoot());
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setWindowAnimations(R.style.BottomSheetAnimation);
                dialog.getWindow().getAttributes().windowAnimations = R.style.BottomSheetAnimation;
                layoutBinding.btnbacklogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                layoutBinding.btnDangKi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String picture = "";
                        Date now = new Date();
                        // Định dạng ngày tháng năm theo định dạng "ddMMyyyy"
                        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
                        String formattedDate = dateFormat.format(now);
                        // Tạo mã khách hàng với tiền tố "KH" + ngày tháng năm
                        String id = "KH0" + formattedDate;
                        String fullname = layoutBinding.edtName.getText().toString();
                        String username = layoutBinding.edtuserdangnhap.getText().toString();
                        String password = layoutBinding.edtmatkhau.getText().toString();
                        String rePassword = layoutBinding.edtxacnhanmatkhau.getText().toString();
                        String numberPhone = layoutBinding.edtphone.getText().toString();
                        String email = layoutBinding.edtmail.getText().toString();
                        String address = layoutBinding.edtdiachi.getText().toString();
                        String sex = "";
                        if (layoutBinding.rdnam.isChecked()) {
                            sex = "Nam";
                        } else if (layoutBinding.rdnu.isChecked()) {
                            sex = "Nữ";
                        }
                        if (fullname.isEmpty() || username.isEmpty() || password.isEmpty() || rePassword.isEmpty() ||
                                numberPhone.isEmpty() || email.isEmpty() || address.isEmpty()) {
                            // Nếu có trường nào rỗng, thông báo lỗi
                            Toast.makeText(dialog.getContext(), "Vui lòng điền đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                        } else if (!password.equals(rePassword)) {
                            // Nếu mật khẩu và mật khẩu xác nhận không khớp, thông báo lỗi
                            Toast.makeText(dialog.getContext(), "Mật khẩu xác nhận không khớp.", Toast.LENGTH_SHORT).show();
                        } else {
                            CustomerModels newCustomer = new CustomerModels(id, password, username, sex, new Date(), fullname, address, numberPhone, email, picture, 0, 0, "");
                            ApiService apiService = ApiClient.getClient().create(ApiService.class);
                            apiService.addCustomer(newCustomer).enqueue(new Callback<CustomerModels>() {
                                @Override
                                public void onResponse(Call<CustomerModels> call, Response<CustomerModels> response) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(dialog.getContext());
                                    builder.setTitle("Đăng ký thành công!");

                                    Drawable icon = getResources().getDrawable(R.drawable.baseline_verified_user_24);
                                    icon.setColorFilter(ContextCompat.getColor(dialog.getContext(), R.color.yellow), PorterDuff.Mode.SRC_IN);
                                    builder.setIcon(icon);
                                    builder.setMessage("Vui lòng đăng nhập?");
                                    builder.setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent = new Intent(dialog.getContext(), LoginActivity.class);
                                            startActivity(intent);
                                        }
                                    });

                                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                            dialog.dismiss();
                                            finish();
                                        }
                                    });
                                    Dialog dialog = builder.create();
                                    dialog.setCanceledOnTouchOutside(false);
                                    dialog.show();
                                }

                                @Override
                                public void onFailure(Call<CustomerModels> call, Throwable t) {
                                    Log.e("API Error", t.getMessage());
                                }
                            });
                        }
                    }
                });
                dialog.show();
            }
        });
    }
}