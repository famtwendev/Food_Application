package com.example.models;

import java.io.Serializable;

public class FoodModels implements Serializable {

    private int idFood;

    private String nameFood;

    private String pictureFood;

    private String sizeFood;

    private String descriptionFood;

    private double priceFood;

    private String idSupplier; // Mã Nhà cung cấp

    private String idVoucher;

    private int numberInCart;

    public FoodModels(int idFood, String nameFood, String pictureFood, String sizeFood, String descriptionFood, double priceFood, String idSupplier, String idVoucher) {
        this.idFood = idFood;
        this.nameFood = nameFood;
        this.pictureFood = pictureFood;
        this.sizeFood = sizeFood;
        this.numberInCart = 0;
        this.descriptionFood = descriptionFood;
        this.priceFood = priceFood;
        this.idSupplier = idSupplier;
        this.idVoucher = idVoucher;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getPictureFood() {
        return pictureFood;
    }

    public void setPictureFood(String pictureFood) {
        this.pictureFood = pictureFood;
    }

    public String getSizeFood() {
        return sizeFood;
    }

    public void setSizeFood(String sizeFood) {
        this.sizeFood = sizeFood;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public String getDescriptionFood() {
        return descriptionFood;
    }

    public void setDescriptionFood(String descriptionFood) {
        this.descriptionFood = descriptionFood;
    }

    public double getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(double priceFood) {
        this.priceFood = priceFood;
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(String idVoucher) {
        this.idVoucher = idVoucher;
    }
}
