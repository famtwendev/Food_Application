package com.example.food_application.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.food_application.R;
import com.example.food_application.activity.LoginActivity;
import com.example.controller.UserPreferences;
import com.example.food_application.databinding.FragmentCustomerBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerFragment extends Fragment {

    private FragmentCustomerBinding binding;
    private static final int LOGIN_REQUEST_CODE = 1;
    private UserPreferences userPreferences;

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
        Log.e("CustomerFragment", "onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        userPreferences = new UserPreferences(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("CustomerFragment", "onCreateView");
        binding = FragmentCustomerBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        addEvents();

        return view;
    }

    private void addEvents() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xóa thông tin người dùng
                userPreferences.destroy();
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
                if (userPreferences == null) {
                    binding.btnLogin.performClick();
                }
            }
        });
        binding.btnshopeecoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userPreferences == null) {
                    binding.btnLogin.performClick();
                }
            }
        });

        binding.btnmyaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userPreferences == null) {
                    binding.btnLogin.performClick();
                }
            }
        });
        binding.btnpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userPreferences == null) {
                    binding.btnLogin.performClick();
                }
            }
        });

        binding.btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userPreferences == null) {
                    binding.btnLogin.performClick();
                }
            }
        });

        binding.btnsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(requireContext());
                dialog.setContentView(R.layout.activity_helper);
                dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
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
        binding.btnaboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userPreferences == null) {
                    binding.btnLogin.performClick();
                }
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
        if (userPreferences.gethasData()) {
            binding.btnLogin.setVisibility(View.GONE);
            binding.btnLogout.setVisibility(View.VISIBLE);
            binding.txtusername.setVisibility(View.VISIBLE);
            binding.txtusername.setText(userPreferences.getFullname());
            binding.imvUser.setImageResource(userPreferences.getPicture());
        } else {
            Log.e("CustomerFragment", "hasData : NULL");
        }
        binding.getRoot().requestLayout();
    }
}