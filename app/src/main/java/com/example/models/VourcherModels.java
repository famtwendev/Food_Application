package com.example.models;

public class VourcherModels {
    private String idVoucher;

    private String nameVourcher;

    private double discount;

    private double percent;

    public VourcherModels(String idVoucher, String nameVourcher, double discount, double percent) {
        this.idVoucher = idVoucher;
        this.nameVourcher = nameVourcher;
        this.discount = discount;
        this.percent = percent;
    }

    public String getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(String idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getNameVourcher() {
        return nameVourcher;
    }

    public void setNameVourcher(String nameVourcher) {
        this.nameVourcher = nameVourcher;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
