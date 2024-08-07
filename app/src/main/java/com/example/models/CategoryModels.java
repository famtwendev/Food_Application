package com.example.models;

public class CategoryModels {
    private int idCategory;
    private String titleCategory;
    private int picCategory;

    public CategoryModels(int idCategory, String titleCategory, int picCategory) {
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

    public int getPicCategory() {
        return picCategory;
    }

    public void setPicCategory(int picCategory) {
        this.picCategory = picCategory;
    }
}
