package com.example.food_application.helper;

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
}
