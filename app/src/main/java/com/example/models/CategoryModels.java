package com.example.models;

import java.io.Serializable;

public class CategoryModels implements Serializable {
    private int idCategory;
    private String titleCategory;
    private String picCategory;

    public CategoryModels(int idCategory, String titleCategory, String picCategory) {
        this.idCategory = idCategory;
        this.titleCategory = titleCategory;
        this.picCategory = picCategory;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getTitleCategory() {
        return titleCategory;
    }

    public void setTitleCategory(String titleCategory) {
        this.titleCategory = titleCategory;
    }

    public String getPicCategory() {
        return picCategory;
    }

    public void setPicCategory(String picCategory) {
        this.picCategory = picCategory;
    }

    @Override
    public String toString() {
        return "CategoryModels{" +
                "idCategory=" + idCategory +
                ", titleCategory='" + titleCategory + '\'' +
                ", picCategory='" + picCategory + '\'' +
                '}';
    }
}
