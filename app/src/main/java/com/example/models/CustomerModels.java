package com.example.models;

import java.util.Date;

public class CustomerModels {
    private String idCustomer;

    private String password;

    private String fullname;

    private String username;

    private String sex;

    private Date birthday;

    private String address;

    private String numnerPhone;

    private String email;

    private String picture;

    private int isValue;

    private int scoreRating;

    private String randomKey;

    public CustomerModels(String idCustomer, String password, String username, String sex, Date birthday, String fullname, String address, String numnerPhone, String email,  String picture, int isValue, int scoreRating, String randomKey) {
        this.idCustomer = idCustomer;
        this.password = password;
        this.fullname = fullname;
        this.username = username;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.numnerPhone = numnerPhone;
        this.email = email;
        this.picture = picture;
        this.isValue = isValue;
        this.scoreRating = scoreRating;
        this.randomKey = randomKey;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumnerPhone() {
        return numnerPhone;
    }

    public void setNumnerPhone(String numnerPhone) {
        this.numnerPhone = numnerPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getIsValue() {
        return isValue;
    }

    public void setIsValue(int isValue) {
        this.isValue = isValue;
    }

    public int getScoreRating() {
        return scoreRating;
    }

    public void setScoreRating(int scoreRating) {
        this.scoreRating = scoreRating;
    }

    public String getRandomKey() {
        return randomKey;
    }

    public void setRandomKey(String randomKey) {
        this.randomKey = randomKey;
    }
}
