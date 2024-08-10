package com.example.food_application.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.food_application.R;
import com.example.food_application.adapter.CategoryAdaptor;
import com.example.food_application.adapter.PopularAdaptor;
import com.example.food_application.adapter.SupplierAdapter;
import com.example.controller.UserPreferences;
import com.example.food_application.databinding.FragmentHomeBinding;
import com.example.models.CategoryModels;
import com.example.models.FoodModels;
import com.example.models.SupplierModels;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView.Adapter adapterRycycleview;

    private UserPreferences userPreferences;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private FragmentHomeBinding binding;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("HomeFragment", "onCreate called");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        userPreferences = new UserPreferences(getContext());
    }

    private void addEvents() {
        binding.txtmyAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "Thay đổi địa chỉ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("HomeFragment", "onCreateView called");

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        sliderbarData();

        recyclerViewCatrgory();

        recyclerViewPopular();

        recycleViewsupplier();

        addEvents();

        return view;
    }

    @Override
    public void onDestroyView() {
        Log.e("HomeFragment", "onDestroyView called");
        super.onDestroyView();
        binding = null;
    }

    private void sliderbarData(){
        ImageSlider imageSlider = binding.slider;

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.slider1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider5, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
    }


    private void recyclerViewCatrgory()
    {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2, GridLayoutManager.HORIZONTAL, false);
        binding.recCategories.setLayoutManager(gridLayoutManager);

        ArrayList<CategoryModels> category = new ArrayList<>();
        category.add(new CategoryModels(9,"Mã giảm giá",R.drawable.cat_9));
        category.add(new CategoryModels(10,"Miễn phí ship",R.drawable.cat_10));
        category.add(new CategoryModels(11,"Khung giờ Sale",R.drawable.cat_11));
        category.add(new CategoryModels(6,"Bún, Phở",R.drawable.cat_6));
        category.add(new CategoryModels(7,"Trà sữa",R.drawable.cat_7));
        category.add(new CategoryModels(8,"Cơm",R.drawable.cat_8));
        category.add(new CategoryModels(1,"Pizza",R.drawable.cat_1));
        category.add(new CategoryModels(2,"HamBurger",R.drawable.cat_2));
        category.add(new CategoryModels(3,"Bánh mì",R.drawable.cat_3));
        category.add(new CategoryModels(4,"Nước ngọt",R.drawable.cat_4));
        category.add(new CategoryModels(5,"Bánh ngọt",R.drawable.cat_5));

        adapterRycycleview= new CategoryAdaptor(category);
        binding.recCategories.setAdapter(adapterRycycleview);
    }

    private void recyclerViewPopular()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.recPopular.setLayoutManager(linearLayoutManager);

        ArrayList<FoodModels> fooditem = new ArrayList<>();
        fooditem.add(new FoodModels(1,"Bún Bò", R.drawable.ic_bunbo,"L","Buổi sáng no nê",35000,6,""));
        fooditem.add(new FoodModels(2,"Phở Bò",  R.drawable.ic_phobo,"L","Buổi sáng no nê",35000,6,""));
        fooditem.add(new FoodModels(3,"Cơm tấm",  R.drawable.ic_comtam,"L","Buổi trưa vui vẻ",32000,8,""));
        fooditem.add(new FoodModels(4,"Bánh canh",  R.drawable.ic_banhcanh,"L","Buổi tối ấm cúng",35000,6,""));
        fooditem.add(new FoodModels(5,"Trà sữa",  R.drawable.ic_trasua,"L","Tráng miệng",25000,7,""));
        fooditem.add(new FoodModels(6,"Pizza",  R.drawable.ic_pizza,"L","Tráng miệng",55000,1,""));
        fooditem.add(new FoodModels(7,"Bún đậu",  R.drawable.ic_bundau,"L","Buổi tối ấm cúng",75000,10,""));
        fooditem.add(new FoodModels(8,"Bánh ngọt",  R.drawable.ic_banhngot,"L","Tráng miệng",35000,5,""));
        fooditem.add(new FoodModels(9,"Pepsi",  R.drawable.ic_pepsi,"L","Giải khát",10000,4,""));
        fooditem.add(new FoodModels(10,"Bánh mì thịt",  R.drawable.ic_banhmi,"L","Bữa sáng no nê",25000,3,""));


        adapterRycycleview= new PopularAdaptor(fooditem);
        binding.recPopular.setAdapter(adapterRycycleview);
    }


    private void recycleViewsupplier() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false);
        binding.recSupplier.setLayoutManager(linearLayoutManager);

        ArrayList<SupplierModels> supplierList =  new ArrayList<>();;
        supplierList.add(new SupplierModels("1", "Kichi-Kichi Buffet Lẩu Băng Chuyền", R.drawable.shop_kichikichi, 4, 1.2, 1));
        supplierList.add(new SupplierModels("2", "Dooki LẨU HÀN QUỐC TOKBOKKI BUFFET DOKKI", R.drawable.shop_dokkie,5, 1.0, 1));
        supplierList.add(new SupplierModels("3", "Hồng trà ngô gia", R.drawable.shop_hongtrangogia,4, 0.8, 1));
        supplierList.add(new SupplierModels("4", "Lotteria", R.drawable.shop_lotteria,3,5.5, 10));
        supplierList.add(new SupplierModels("5", "Hadilao", R.drawable.shop_hadilao,4,2.1,11));
        supplierList.add(new SupplierModels("6", "KFC", R.drawable.food_kfc,4,1.8,2));
        supplierList.add(new SupplierModels("7", "Hủ tiếu Cô Liêu", R.drawable.shop_hutieu, 3,3.7, 15));
        supplierList.add(new SupplierModels("8", "MC Donal", R.drawable.shop_mcdonal, 4,3.9, 20));
        supplierList.add(new SupplierModels("9", "Xôi Mặn Hoàng Tử", R.drawable.ic_xoiman, 3,2.6, 8));

        adapterRycycleview= new SupplierAdapter(supplierList);
        binding.recSupplier.setAdapter(adapterRycycleview);
    }

    private void loadData() {
        if (userPreferences.gethasData()) {
            binding.txtmyAddress.setText(userPreferences.getAddress());
            Log.e("HomeFragment", "!NULL");

        } else {
            binding.txtmyAddress.setText(R.string.my_address);
            Log.e("HomeFragment", "NULL");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
}
