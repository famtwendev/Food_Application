package com.example.food_application.TableLayoutFavorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.SupplierAdapter;
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
        supplierList.add(new SupplierModels("1", "Kichi-Kichi Buffet Lẩu Băng Chuyền", R.drawable.shop_kichikichi, 4, 1.2, 1));
        supplierList.add(new SupplierModels("2", "Dooki LẨU HÀN QUỐC TOKBOKKI BUFFET DOKKI", R.drawable.shop_dokkie,5, 1.0, 1));
        supplierList.add(new SupplierModels("3", "Hồng trà ngô gia", R.drawable.shop_hongtrangogia,4, 0.8, 1));
        supplierList.add(new SupplierModels("4", "Lotteria", R.drawable.shop_lotteria,3,5.5, 10));

        adapterRycycleview= new SupplierAdapter(supplierList);
        binding.recCotheCB.setAdapter(adapterRycycleview);


    }
}
