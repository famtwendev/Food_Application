package com.example.food_application.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.food_application.R;
import com.example.food_application.databinding.ActivityInfoBinding;
import com.example.food_application.helper.ManagementUser;
import com.example.models.ApiClient;
import com.example.models.ApiService;
import com.example.models.CustomerModels;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends AppCompatActivity {

    private ActivityInfoBinding binding;

    private ManagementUser managementUser;

    private ArrayList<CustomerModels> customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        managementUser = new ManagementUser(this);
        binding = ActivityInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();

        binding.btnbacklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = TextUtils.isEmpty(binding.edtFullname.getText().toString()) ? managementUser.getFullname() : binding.edtFullname.getText().toString();
                String address = TextUtils.isEmpty(binding.edtAddress.getText().toString()) ? managementUser.getAddress() : binding.edtAddress.getText().toString();

                String dateString = binding.edtBirthday.getText().toString();
                Date birthday;

                if (TextUtils.isEmpty(dateString)) {
                    // Nếu EditText rỗng, gán giá trị từ managementUser
                    birthday = managementUser.getBirthday();
                } else {
                    // Chuyển đổi chuỗi ngày tháng thành đối tượng Date
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()); // Thay đổi định dạng nếu cần
                    try {
                        birthday = dateFormat.parse(dateString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        // Xử lý lỗi khi không thể phân tích chuỗi ngày tháng
                        birthday = managementUser.getBirthday(); // Hoặc gán giá trị mặc định khác
                    }
                }

                String email = TextUtils.isEmpty(binding.edtMail.getText().toString()) ? String.valueOf(managementUser.getEmail()) : binding.edtMail.getText().toString();
                String numberPhone = TextUtils.isEmpty(binding.edtPhone.getText().toString()) ? String.valueOf(managementUser.getNumberPhone()) : binding.edtPhone.getText().toString();
                String sex;
                if (binding.rdioNam.isChecked()) {
                    sex = "Nam";
                } else {
                    sex = "Nữ";
                }
                CustomerModels newCustomer = new CustomerModels(managementUser.getIdCustomer(), managementUser.getPassword(), managementUser.getUsername(), sex, birthday, fullname, address, numberPhone, email, managementUser.getPicture(), managementUser.isValue(), managementUser.getScoreRating(), managementUser.getRandomKey());
                ApiService apiService = ApiClient.getClient().create(ApiService.class);
                apiService.updateCustomer(managementUser.getIdCustomer(), newCustomer).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                        builder.setTitle("Cập nhật thông tin thành công!");

                        Drawable icon = getResources().getDrawable(R.drawable.baseline_verified_user_24);
                        icon.setColorFilter(ContextCompat.getColor(InfoActivity.this, R.color.yellow), PorterDuff.Mode.SRC_IN);
                        builder.setIcon(icon);
                        builder.setMessage("Sử dụng dịch vụ khác ?");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                InfoActivity.this.onResume();
                            }
                        });

                        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });

                        Dialog dialog = builder.create();
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("API Error", t.getMessage());
                    }
                });
            }
        });
    }

    private void getData() {
        if (managementUser.gethasData()) {
            binding.edtFullname.setText(managementUser.getFullname());
            binding.edtAddress.setText(managementUser.getAddress());
            binding.edtBirthday.setText(String.valueOf(managementUser.getBirthday()));
            binding.edtMail.setText(String.valueOf(managementUser.getEmail()));
            binding.edtPhone.setText(String.valueOf(managementUser.getNumberPhone()));
            if ("Nam".equals(managementUser.getSex())) {
                binding.rdioNam.setChecked(true);
            } else if("Nữ".equals(managementUser.getSex())) {
                binding.rdioNu.setChecked(true);
            }
            int drawableResourceId = binding.getRoot().getContext().getResources().getIdentifier(managementUser.getPicture(), "drawable", binding.getRoot().getContext().getPackageName());
            binding.imvPhto.setImageResource(drawableResourceId);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}