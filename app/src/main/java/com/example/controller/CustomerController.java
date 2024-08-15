package com.example.controller;
import com.example.food_application.R;
import com.example.models.CustomerModels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
public class CustomerController {
    private static final List<CustomerModels> accountList = new ArrayList<>();

    static{
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            accountList.add(new CustomerModels("1", "admin", "admin", "phamtuyen121314@gmail.com", "0787675747", "Nguyễn Phạm Tuyên", "Nam", formatter.parse("2003-10-22"), "Phường 25 ,Quận Bình Thạnh, TP. Hồ Chí Minh","ic_userimage", true, 0, "randomKey1"));
            accountList.add(new CustomerModels("2", "nhatvu123","admin", "nhatvu123@gmail.com", "0909010203", "Trần Nhật Vũ", "Nữ", formatter.parse("2004-06-05"), "Thảo Điền ,TP. Thủ Đức, TP. Hồ Chí Minh", "ic_userimage2", true, 0, "randomKey2"));
            // Thêm các tài khoản khác nếu cần
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Thêm một số tài khoản mẫu
    }

    public static CustomerModels authenticate(String username, String password) {
        for (CustomerModels account : accountList) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password) || account.getEmail().equals(username) && account.getPassword().equals(password)) {
                return account; // Đăng nhập thành công
            }
        }
        return null; // Đăng nhập thất bại
    }
}

