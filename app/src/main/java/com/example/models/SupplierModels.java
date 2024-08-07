package com.example.models;

public class SupplierModels {
    private String idSupplier;

    private String nameSupplier;

    private int pictureSupplier;

    private int ratingSupplier;

    private double distanceSupplier;

    private int timeSupplier;

    public SupplierModels(String idSupplier, String nameSupplier, int pictureSupplier, int ratingSupplier, double distanceSupplier, int timeSupplier) {
        this.idSupplier = idSupplier;
        this.nameSupplier = nameSupplier;
        this.pictureSupplier = pictureSupplier;
        this.ratingSupplier = ratingSupplier;
        this.distanceSupplier = distanceSupplier;
        this.timeSupplier = timeSupplier;
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

    public int getPictureSupplier() {
        return pictureSupplier;
    }

    public void setPictureSupplier(int pictureSupplier) {
        this.pictureSupplier = pictureSupplier;
    }

    public int getRatingSupplier() {
        return ratingSupplier;
    }

    public void setRatingSupplier(int ratingSupplier) {
        this.ratingSupplier = ratingSupplier;
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
}
