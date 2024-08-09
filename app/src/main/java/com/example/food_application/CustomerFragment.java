package com.example.food_application;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.controller.UserPreferences;
import com.example.food_application.databinding.FragmentCustomerBinding;
import com.example.models.CustomerModels;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerFragment extends Fragment {

    private FragmentCustomerBinding binding;
    private static final int LOGIN_REQUEST_CODE = 1;
    UserPreferences userPreferences;

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
        Log.e("CustomerFragment", "CustomerFragment called");
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
        Log.e("CustomerFragment", "onCreate called");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("CustomerFragment", "onCreateView called");
        binding = FragmentCustomerBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        addEvents();

        return view;
    }

    private void addEvents() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(requireContext(), LoginActivity.class);
//                startActivity(intent);
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xóa thông tin người dùng
                userPreferences.clearUserInfo();
                // Cập nhật giao diện
                binding.btnLogin.setVisibility(View.VISIBLE);
                binding.txtusername.setVisibility(View.GONE);
                binding.btnLogout.setVisibility(View.GONE);
            }
        });

        binding.btnvoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userPreferences == null) {
                    Intent intent = new Intent(requireContext(), LoginActivity.class);
                    startActivityForResult(intent, LOGIN_REQUEST_CODE);
                }
            }
        });
        binding.btnshopeecoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userPreferences == null) {
                    Intent intent = new Intent(requireContext(), LoginActivity.class);
                    startActivityForResult(intent, LOGIN_REQUEST_CODE);
                }
            }
        });

        binding.btnmyaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userPreferences == null) {
                    Intent intent = new Intent(requireContext(), LoginActivity.class);
                    startActivityForResult(intent, LOGIN_REQUEST_CODE);
                }
            }
        });
        binding.btnpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userPreferences == null) {
                    Intent intent = new Intent(requireContext(), LoginActivity.class);
                    startActivityForResult(intent, LOGIN_REQUEST_CODE);
                }
            }
        });

        binding.btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userPreferences == null) {
                    Intent intent = new Intent(requireContext(), LoginActivity.class);
                    startActivityForResult(intent, LOGIN_REQUEST_CODE);
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
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
//                dialog.getWindow().getAttributes().windowAnimations = R.style.BottomSheetAnimation;

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
    }


    private void loadData() {
        userPreferences = new UserPreferences(requireActivity());
        if (userPreferences != null) {
            binding.btnLogin.setVisibility(View.GONE);
            binding.btnLogout.setVisibility(View.VISIBLE);
            binding.txtusername.setVisibility(View.VISIBLE);
            binding.txtusername.setText(userPreferences.getFullname());
        } else {
            Log.e("CustomerFragment", "NULL");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Cập nhật dữ liệu khi quay lại từ LoginActivity
            loadData();
        }
    }
}