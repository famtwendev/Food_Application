package com.example.food_application.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static final String SUPPLIER = "supplier";
    public static final String THISSUPPLIER = "mySupplier";
    public static final String CATEGORY = "category";
    public static final String THISCATEGORY = "myCategory";

    public static Date addDate(   int minutesToAdd){
        long minutesInMillis = minutesToAdd * 60000; // Chuyển đổi phút thành milliseconds
        long newTimeMillis = System.currentTimeMillis() + minutesInMillis; // Thêm số phút vào thời gian hiện tại
        // Nếu bạn muốn in ra thời gian sau khi thêm phút
        Date newDate = new Date(newTimeMillis);
        return newDate;
    }

    public static String generateCustomerCode() {
        // Lấy ngày tháng năm hiện tại
        Date now = new Date();
        // Định dạng ngày tháng năm theo định dạng "ddMMyyyy"
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
        String formattedDate = dateFormat.format(now);
        // Tạo mã khách hàng với tiền tố "KH" + ngày tháng năm
        return formattedDate;
    }
}
