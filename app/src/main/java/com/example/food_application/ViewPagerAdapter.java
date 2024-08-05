package com.example.food_application;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.food_application.bill.BillFragment;
import com.example.food_application.customer.CustomerFragment;
import com.example.food_application.favorite.FavoriteFragment;
import com.example.food_application.home.HomeFragment;
import com.example.food_application.notify.NotifyFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();

            case 1:
                return new BillFragment();

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
