package com.example.food_application.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_application.adapter.SupplierAdapter;
import com.example.food_application.R;
import com.example.food_application.databinding.TabComingBinding;
import com.example.models.SupplierModels;

import java.util.ArrayList;

public class Tabcoming extends Fragment {
    private TabComingBinding binding;
    private RecyclerView.Adapter adapterRycycleview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = TabComingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        recycleViewsupplier();

        return view;
    }

    private void recycleViewsupplier() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false);
        binding.recCotheCB.setLayoutManager(linearLayoutManager);

        ArrayList<SupplierModels> supplierList =  new ArrayList<>();;
        supplierList.add(new SupplierModels("1", "Kichi-Kichi Buffet Lẩu Băng Chuyền", "shop_kichikichi", 4.1,123, "Thủ đức", 1.2, 1, 1));
        supplierList.add(new SupplierModels("2", "Dooki LẨU HÀN QUỐC TOKBOKKI BUFFET DOKKI", "shop_dokkie", 4.2,11, "Thủ đức", 1.0, 1, 2));
        supplierList.add(new SupplierModels("3", "Hồng trà ngô gia", "shop_hongtrangogia", 5.0, 102,"Thủ đức", 0.8, 1, 3));
        supplierList.add(new SupplierModels("4", "Lotteria", "shop_lotteria", 3.2,78, "Thủ đức", 5.5, 10, 4));
        adapterRycycleview= new SupplierAdapter(supplierList);
        binding.recCotheCB.setAdapter(adapterRycycleview);


    }
}
