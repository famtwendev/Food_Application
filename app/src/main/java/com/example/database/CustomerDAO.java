package com.example.database;

import com.example.models.CustomerModels;

import java.util.ArrayList;

public class CustomerDAO implements DAOInterface<CustomerModels> {

    private ArrayList<CustomerModels> data = new ArrayList<>();

    @Override
    public ArrayList<CustomerModels> selectAll() {
        // TODO Auto-generated method stub
        return this.data;
    }

    @Override
    public CustomerModels selectById(CustomerModels t) {
        // TODO Auto-generated method stub
        for (CustomerModels KhachHang : data) {
            if (KhachHang.equals(t)) {
                return KhachHang;
            }
        }
        return null;
    }

    @Override
    public int insert(CustomerModels t) {
        if (this.selectById(t) == null) {
            this.data.add(t);
            return 1;
        }
        return 0;
    }

    @Override
    public int insertAll(ArrayList<CustomerModels> arr) {

        int dem = 0;
        for (CustomerModels KhachHang : arr) {
            this.insert(KhachHang);
        }
        return dem;
    }

    @Override
    public int delete(CustomerModels t) {
        if (this.selectById(t) != null) {
            this.data.remove(t);
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteAll(ArrayList<CustomerModels> arr) {
        int dem = 0;
        for (CustomerModels KhachHang : arr) {
            dem += this.delete(KhachHang);
        }
        return dem;
    }

    @Override
    public int update(CustomerModels t) {
        if (this.selectById(t) != null) {
            this.data.remove(t);
            this.data.add(t);
            return 1;
        }
        return 0;
    }
}
