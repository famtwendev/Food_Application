package com.example.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.food_application.TableLayoutFavorite.Tabcoming;
import com.example.food_application.TableLayoutFavorite.Tabdraft;
import com.example.food_application.TableLayoutFavorite.Tabfavorite;
import com.example.food_application.TableLayoutFavorite.Tabhistory;
import com.example.food_application.TableLayoutFavorite.Tabrating;

public class FavoritePagerTab extends FragmentStateAdapter {

    public FavoritePagerTab(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Tabfavorite();
            case 1:
                return new Tabcoming();
            case 2:
                return new Tabhistory();
            case 3:
                return new Tabrating();
            case 4:
                return new Tabdraft();
            default:
                return new Tabfavorite();
        }
    }

    @Override
    public int getItemCount() {
        return 5; // Number of tabs
    }
}