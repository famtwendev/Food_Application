package com.example.models;

import java.util.Date;

public class VourcherModels {
    private String idVoucher; // mã giảm giá

    private String nameVourcher; //  tên giảm giá

    private double discount; // tiền giảm giá

    private double percent; // phần trăm

    private Date soldout; // thời hạn hết

    private String vourchertype; // loại vourcher

    public VourcherModels(String idVoucher, String nameVourcher, double discount, double percent, Date soldout, String vourchertype) {
        this.idVoucher = idVoucher;
        this.nameVourcher = nameVourcher;
        this.discount = discount;
        this.percent = percent;
        this.soldout = soldout;
        this.vourchertype = vourchertype;
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

    public Date getSoldout() {
        return soldout;
    }

    public void setSoldout(Date soldout) {
        this.soldout = soldout;
    }

    public String getVourchertype() {
        return vourchertype;
    }

    public void setVourchertype(String vourchertype) {
        this.vourchertype = vourchertype;
    }
}
