package com.example.food_application.activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_application.databinding.ActivityDetailFoodBinding;
import com.example.food_application.helper.ManagementCart;
import com.example.models.FoodModels;

import java.text.DecimalFormat;

public class DetailFoodActivity extends AppCompatActivity {


    ActivityDetailFoodBinding binding;


    private FoodModels object;
    private int numberOrder = 1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDetailFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        managementCart = new ManagementCart(this);

        getBundle();
    }

    private void getBundle() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        object = (FoodModels) getIntent().getSerializableExtra("object");

        int drawableResourceId = binding.getRoot().getContext().getResources().getIdentifier(object.getPictureFood(), "drawable", binding.getRoot().getContext().getPackageName());

        binding.imvProduct.setImageResource(drawableResourceId);
        binding.txtProductName.setText(object.getNameFood());
        binding.txtdescription.setText("Mô tả: "+object.getDescriptionFood());
        binding.txtProductPrice.setText(decimalFormat.format(object.getPriceFood()));
        binding.txtAmmount.setText(String.valueOf(numberOrder));

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder = numberOrder + 1;
                binding.txtAmmount.setText(String.valueOf(numberOrder));
            }
        });

        binding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                    binding.txtAmmount.setText(String.valueOf(numberOrder));
                } else {
                    binding.txtAmmount.setText(String.valueOf(numberOrder));
                }
            }
        });

        binding.btnAddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
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