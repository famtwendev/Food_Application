package com.example.models;

import java.io.Serializable;

public class SupplierModels implements Serializable {
    private String idSupplier;

    private String nameSupplier;

    private String pictureSupplier;

    private double ratingSupplier;

    private int ratingCount;

    private String addressSupplier;

    private int idCategory;

    private double distanceSupplier;

    private int timeSupplier;

    @Override
    public String toString() {
        return "SupplierModels{" +
                "idSupplier='" + idSupplier + '\'' +
                ", nameSupplier='" + nameSupplier + '\'' +
                ", pictureSupplier='" + pictureSupplier + '\'' +
                ", ratingSupplier=" + ratingSupplier +
                ", addressSupplier='" + addressSupplier + '\'' +
                ", idCategory=" + idCategory +
                ", distanceSupplier=" + distanceSupplier +
                ", timeSupplier=" + timeSupplier +
                '}';
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getNameSupplier() {
        return nameSupplier;
    }

    public void setNameSupplier(String nameSupplier) {
        this.nameSupplier = nameSupplier;
    }

    public String getPictureSupplier() {
        return pictureSupplier;
    }

    public void setPictureSupplier(String pictureSupplier) {
        this.pictureSupplier = pictureSupplier;
    }

    public double getRatingSupplier() {
        return ratingSupplier;
    }

    public void setRatingSupplier(double ratingSupplier) {
        this.ratingSupplier = ratingSupplier;
    }

    public void setRatingSupplier(int ratingSupplier) {
        this.ratingSupplier = ratingSupplier;
    }

    public String getAddressSupplier() {
        return addressSupplier;
    }

    public void setAddressSupplier(String addressSupplier) {
        this.addressSupplier = addressSupplier;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public double getDistanceSupplier() {
        return distanceSupplier;
    }

    public void setDistanceSupplier(double distanceSupplier) {
        this.distanceSupplier = distanceSupplier;
    }

    public int getTimeSupplier() {
        return timeSupplier;
    }

    public void setTimeSupplier(int timeSupplier) {
        this.timeSupplier = timeSupplier;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public SupplierModels(String idSupplier, String nameSupplier, String pictureSupplier, double ratingSupplier, int ratingCount, String addressSupplier, double distanceSupplier, int timeSupplier, int idCategory) {
        this.idSupplier = idSupplier;
        this.nameSupplier = nameSupplier;
        this.pictureSupplier = pictureSupplier;
        this.ratingSupplier = ratingSupplier;
        this.ratingCount = ratingCount;
        this.addressSupplier = addressSupplier;
        this.idCategory = idCategory;
        this.distanceSupplier = distanceSupplier;
        this.timeSupplier = timeSupplier;
    }
}
