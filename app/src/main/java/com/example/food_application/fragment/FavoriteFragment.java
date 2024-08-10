package com.example.food_application.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.food_application.R;
import com.example.food_application.adapter.ViewPageFavoriteFragmentAdapter;
import com.example.food_application.databinding.FragmentFavoriteBinding;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {


    private FragmentFavoriteBinding binding;
    private ViewPager2 viewPager;
    private ViewPageFavoriteFragmentAdapter adapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavoriteFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Favorite_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewPager = view.findViewById(R.id.viewPagerFavorite);
        viewPager.setOffscreenPageLimit(2);
        // Disable swipe
        viewPager.setUserInputEnabled(false);
        adapter = new ViewPageFavoriteFragmentAdapter(requireActivity());

        viewPager.setAdapter(adapter);

        new TabLayoutMediator(binding.tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Yêu thích");
                    break;
                case 1:
                    tab.setText("Đang đến");
                    break;
                case 2:
                    tab.setText("Lịch sử");
                    break;
                case 3:
                    tab.setText("Đánh giá");
                    break;
            }
        }).attach();
        return view;
    }
}