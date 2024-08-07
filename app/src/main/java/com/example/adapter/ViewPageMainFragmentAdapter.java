package com.example.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.food_application.CartFragment;
import com.example.food_application.CustomerFragment;
import com.example.food_application.FavoriteFragment;
import com.example.food_application.HomeFragment;
import com.example.food_application.NotifyFragment;

public class ViewPageMainFragmentAdapter extends FragmentStateAdapter {

    public ViewPageMainFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();

            case 1:
                return new CartFragment();

            case 2:
                return new FavoriteFragment();

            case 3:
                return new NotifyFragment();

            case 4:
                return new CustomerFragment();

            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }


}
