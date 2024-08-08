package com.example.food_application;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.food_application.databinding.FragmentCustomerBinding;
import com.example.food_application.databinding.FragmentFavoriteBinding;
import com.example.food_application.databinding.FragmentHomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerFragment extends Fragment {

    private FragmentCustomerBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustomerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerFragment newInstance(String param1, String param2) {
        CustomerFragment fragment = new CustomerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCustomerBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        addEvents();

        return view;
    }

    private void addEvents() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheet();
//                binding.btnLogin.setVisibility(View.GONE);
//                binding.txtusername.setVisibility(View.VISIBLE);
//                binding.btnLogout.setVisibility(View.VISIBLE);
            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnLogin.setVisibility(View.VISIBLE);
                binding.txtusername.setVisibility(View.GONE);
                binding.btnLogout.setVisibility(View.GONE);
            }
        });
    }
    private void showBottomSheet() {
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.activity_login);

        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        dialog.getWindow().getAttributes().windowAnimations = R.style.BottomSheetAnimation;

        //Set button click
        addEventsForDialog(dialog);

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void addEventsForDialog(Dialog dialog) {
        dialog.findViewById(R.id.btnbacklogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.btnLoginMatKhau).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.findViewById(R.id.logindefault).setVisibility(View.GONE);
                dialog.findViewById(R.id.loginbangmatkhau).setVisibility(View.VISIBLE);
                dialog.findViewById(R.id.btnLoginMatKhau).setVisibility(View.GONE);


                dialog.findViewById(R.id.row2login).requestLayout();
                dialog.show();
           }
        });
    }
}