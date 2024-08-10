package com.example.food_application;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controller.CustomerController;
import com.example.controller.UserPreferences;
import com.example.food_application.databinding.ActivityLoginBinding;
import com.example.models.CustomerModels;

public class LoginActivity extends AppCompatActivity {

    private static final int LOGIN_REQUEST_CODE = 1;
    private UserPreferences userPreferences;
    ActivityLoginBinding binding;

    private boolean isHidden = true; // Dang an

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userPreferences = new UserPreferences(LoginActivity.this);

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
                CustomerModels account = CustomerController.authenticate(username, password);

                if (account != null) {
                    userPreferences.saveUserInfo(
                            account.getIdCustomer(), // idCustomer
                            account.getPassword(), // password
                            account.getFullname(), // fullname
                            account.getUsername(), // username
                            account.getSex(), // sex
                            account.getBirthday(), // birthday
                            account.getAddress(), // address
                            account.getNumnerPhone(), // numberPhone
                            account.getEmail(), // email
                            account.getPicture(), // picture
                            account.isValue(), // isValue
                            account.getScoreRating(), // scoreRating
                            account.getRandomKey() // randomKey
                    );
                    userPreferences.setHasData();
                    setResult(Activity.RESULT_OK); // Trả kết quả thành công
                    Log.e("LoginActivity", "Success");
                    finish();

                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                } else {
                    // Đăng nhập thất bại, thông báo cho người dùng
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại. Vui lòng kiểm tra lại thông tin.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.edtUsernameEmail.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // Kiểm tra nếu phím Tab được nhấn
                if (keyCode == KeyEvent.KEYCODE_TAB && event.getAction() == KeyEvent.ACTION_DOWN) {
                    binding.edtPassword.requestFocus(); // Di chuyển focus đến edtPassword
                    return true;
                }
                return false;
            }
        });

        binding.edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // Kiểm tra nếu phím Enter được nhấn
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    binding.btnDangNhap.performClick(); // Gọi sự kiện click của nút đăng nhập
                    return true;
                } else if (keyCode == KeyEvent.KEYCODE_TAB && event.getAction() == KeyEvent.ACTION_DOWN) {
                    binding.btnDangNhap.requestFocus(); // Di chuyển focus đến btnDangNhap nếu nhấn Tab
                    return true;
                }
                return false;
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
    }
}