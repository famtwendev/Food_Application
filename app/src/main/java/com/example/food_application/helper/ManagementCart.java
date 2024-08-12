package com.example.food_application.helper;

import android.content.Context;
import android.widget.Toast;

import com.example.models.FoodModels;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;


    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(FoodModels item)
    {
        ArrayList<FoodModels> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if(listFood.get(i).getNameFood().equals(item.getNameFood())){
                existAlready = true;
                n = i;
                break;
            }

        }
        if(existAlready)
        {
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            listFood.add(item);
        }
        tinyDB.putListObject("CartList", listFood);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
    }

    private ArrayList<FoodModels> getListCart() {
        return tinyDB.getListObject("CartList");
    }
}
