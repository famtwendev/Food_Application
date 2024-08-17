package com.example.models;

import java.util.Date;

public class BillModels {
    private String idBill;

    private String idCustomer;

    private Date dateOrder;

    private Date dateExpected;

    private Date dateDelivery;

    private String namebill;

    private String addressbill;

    private String email;

    private String numberPhone;

    private String methodPayment;

    private double totalfee;

    private double payfee;

    private String transporters;

    private double feeDelivery;

    private String MaTrangThai;

    private String note;

    private String idVoucher;

    public BillModels(String idBill, String idCustomer, Date dateOrder, Date dateExpected, Date dateDelivery, String namebill, String addressbill, String email, String numberPhone, String methodPayment, double totalfee, double payfee, String transporters, double feeDelivery, String maTrangThai, String note, String idVoucher) {
        this.idBill = idBill;
        this.idCustomer = idCustomer;
        this.dateOrder = dateOrder;
        this.dateExpected = dateExpected;
        this.dateDelivery = dateDelivery;
        this.namebill = namebill;
        this.addressbill = addressbill;
        this.email = email;
        this.numberPhone = numberPhone;
        this.methodPayment = methodPayment;
        this.totalfee = totalfee;
        this.payfee = payfee;
        this.transporters = transporters;
        this.feeDelivery = feeDelivery;
        this.MaTrangThai = maTrangThai;
        this.note = note;
        this.idVoucher = idVoucher;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Date getDateExpected() {
        return dateExpected;
    }

    public void setDateExpected(Date dateExpected) {
        this.dateExpected = dateExpected;
    }

    public Date getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(Date dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public String getNamebill() {
        return namebill;
    }

    public void setNamebill(String namebill) {
        this.namebill = namebill;
    }

    public String getAddressbill() {
        return addressbill;
    }

    public void setAddressbill(String addressbill) {
        this.addressbill = addressbill;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getMethodPayment() {
        return methodPayment;
    }

    public void setMethodPayment(String methodPayment) {
        this.methodPayment = methodPayment;
    }

    public double getTotalfee() {
        return totalfee;
    }

    public void setTotalfee(double totalfee) {
        this.totalfee = totalfee;
    }

    public double getPayfee() {
        return payfee;
    }

    public void setPayfee(double payfee) {
        this.payfee = payfee;
    }

    public String getTransporters() {
        return transporters;
    }

    public void setTransporters(String transporters) {
        this.transporters = transporters;
    }

    public double getFeeDelivery() {
        return feeDelivery;
    }

    public void setFeeDelivery(double feeDelivery) {
        this.feeDelivery = feeDelivery;
    }

    public String getMaTrangThai() {
        return MaTrangThai;
    }

    public void setMaTrangThai(String maTrangThai) {
        MaTrangThai = maTrangThai;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        note = note;
    }

    public String getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(String idVoucher) {
        this.idVoucher = idVoucher;
    }
}
