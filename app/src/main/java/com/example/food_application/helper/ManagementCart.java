package com.example.food_application.helper;

import android.content.Context;
import android.widget.Toast;

import com.example.food_application.interfaces.ChangeNumberItemListener;
import com.example.food_application.interfaces.ClearAllItem;
import com.example.models.FoodModels;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(FoodModels item) {
        ArrayList<FoodModels> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getNameFood().equals(item.getNameFood())) {
                existAlready = true;
                n = i;
                break;
            }

        }
        if (existAlready) {
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listFood.add(item);
        }
        tinyDB.putListObject("CartList", listFood);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodModels> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberFood(ArrayList<FoodModels> listFood, int position, ChangeNumberItemListener changeNumberItemListener) {
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemListener.change();
    }

    public void minusNumbeFood(ArrayList<FoodModels> listFood, int position, ChangeNumberItemListener changeNumberItemListener) {
        if (listFood.get(position).getNumberInCart() == 1) {
            listFood.remove(position);
        } else {
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemListener.change();
    }

    public Double getTotalPrice() {
        ArrayList<FoodModels> listFood = getListCart();
        double price = 0;
        for (int i = 0; i < listFood.size(); i++) {
            price = price + (listFood.get(i).getPriceFood() * listFood.get(i).getNumberInCart());
        }
        return price;
    }

    public void removeItem(ArrayList<FoodModels> listFood, int position, ChangeNumberItemListener changeNumberItemListener) {
        listFood.remove(position);
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemListener.change();
    }

    public void clearAllItems(ArrayList<FoodModels> listFood, ClearAllItem clearallitem) {
        listFood.clear();
        tinyDB.putListObject("CartList", listFood);
        clearallitem.clearAll();
    }
}
