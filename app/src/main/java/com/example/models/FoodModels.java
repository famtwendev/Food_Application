package com.example.models;

import java.io.Serializable;

public class FoodModels implements Serializable {

    private int idFood;

    private String nameFood;

    private int pictureFood;

    private String sizeFood;

    private int numberInCart;

    private String descriptionFood;

    private double priceFood;

    private int idCategory;   //Mã thể loại

    private String idSupplier; // Mã Nhà cung cấp

    private String idVoucher;


    public FoodModels(int idFood, String nameFood, int pictureFood, String sizeFood, String descriptionFood, double priceFood, int idCategory, String idSupplier, String idVoucher, int numberInCart) {
        this.idFood = idFood;
        this.nameFood = nameFood;
        this.pictureFood = pictureFood;
        this.sizeFood = sizeFood;
        this.numberInCart = numberInCart;
        this.descriptionFood = descriptionFood;
        this.priceFood = priceFood;
        this.idCategory = idCategory;
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

    public int getPictureFood() {
        return pictureFood;
    }

    public void setPictureFood(int pictureFood) {
        this.pictureFood = pictureFood;
    }

    public String getSizeFood() {
        return sizeFood;
    }

    public void setSizeFood(String sizeFood) {
        this.sizeFood = sizeFood;
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

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
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

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    @Override
    public String toString() {
        return "FoodModels{" +
                "idFood=" + idFood +
                ", nameFood='" + nameFood + '\'' +
                ", pictureFood=" + pictureFood +
                ", sizeFood='" + sizeFood + '\'' +
                ", numberInCart=" + numberInCart +
                ", descriptionFood='" + descriptionFood + '\'' +
                ", priceFood=" + priceFood +
                ", idCategory=" + idCategory +
                ", idSupplier='" + idSupplier + '\'' +
                ", idVoucher='" + idVoucher + '\'' +
                '}';
    }
}
