package com.example.models;

public class BillDetailModels {
    private int idDetail;

    private String idBill;

    private int idFood;

    private int amount;

    public BillDetailModels(String idBill, int idFood, int amount) {
        this.idBill = idBill;
        this.idFood = idFood;
        this.amount = amount;
    }

    public BillDetailModels(int idDetail, String idBill, int idFood, int amount) {
        this.idDetail = idDetail;
        this.idBill = idBill;
        this.idFood = idFood;
        this.amount = amount;
    }

    public int getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}