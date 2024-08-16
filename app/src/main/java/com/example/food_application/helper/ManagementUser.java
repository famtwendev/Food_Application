package com.example.food_application.helper;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Date;

public class ManagementUser {
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_ID_CUSTOMER = "idCustomer";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_FULLNAME = "fullname";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_SEX = "sex";
    private static final String KEY_BIRTHDAY = "birthday";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_NUMBER_PHONE = "numberPhone";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PICTURE = "picture";
    private static final String KEY_IS_VALUE = "isValue";
    private static final String KEY_SCORE_RATING = "scoreRating";
    private static final String KEY_RANDOM_KEY = "randomKey";
    private static final String KEY_HAS_DATA = "hasData";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public ManagementUser(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveUserInfo(String idCustomer, String password, String fullname, String username, String sex, Date birthday,
                             String address, String numberPhone, String email, String picture, int isValue, int scoreRating, String randomKey) {
        editor.putString(KEY_ID_CUSTOMER, idCustomer);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_FULLNAME, fullname);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_SEX, sex);
        editor.putLong(KEY_BIRTHDAY, birthday.getTime()); // Convert Date to timestamp
        editor.putString(KEY_ADDRESS, address);
        editor.putString(KEY_NUMBER_PHONE, numberPhone);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PICTURE, picture);
        editor.putInt(KEY_IS_VALUE, isValue);
        editor.putInt(KEY_SCORE_RATING, scoreRating);
        editor.putString(KEY_RANDOM_KEY, randomKey);
        editor.apply();
    }

    // Đặt cờ hasData thành true
    public void setHasData() {
        editor.putBoolean(KEY_HAS_DATA, true);
        editor.apply();
    }
    // Kiểm tra xem có dữ liệu người dùng hay không
    public boolean gethasData() {
        return sharedPreferences.getBoolean(KEY_HAS_DATA, false);
    }
    public String getIdCustomer() {
        return sharedPreferences.getString(KEY_ID_CUSTOMER, "");
    }

    public String getPassword() {
        return sharedPreferences.getString(KEY_PASSWORD, "");
    }

    public String getFullname() {
        return sharedPreferences.getString(KEY_FULLNAME, "");
    }

    public String getUsername() {
        return sharedPreferences.getString(KEY_USERNAME, "");
    }

    public String getSex() {
        return sharedPreferences.getString(KEY_SEX, "");
    }

    public Date getBirthday() {
        long timestamp = sharedPreferences.getLong(KEY_BIRTHDAY, -1);
        return timestamp == -1 ? null : new Date(timestamp);
    }

    public String getAddress() {
        return sharedPreferences.getString(KEY_ADDRESS, "");
    }

    public String getNumberPhone() {
        return sharedPreferences.getString(KEY_NUMBER_PHONE, "");
    }

    public String getEmail() {
        return sharedPreferences.getString(KEY_EMAIL, "");
    }

    public String getPicture() {
        return sharedPreferences.getString(KEY_PICTURE, "");
    }

    public int isValue() {
        return sharedPreferences.getInt(KEY_IS_VALUE, 0);
    }

    public int getScoreRating() {
        return sharedPreferences.getInt(KEY_SCORE_RATING, 0);
    }

    public String getRandomKey() {
        return sharedPreferences.getString(KEY_RANDOM_KEY, "");
    }

    public void destroy() {
        editor.remove(KEY_ID_CUSTOMER);
        editor.remove(KEY_PASSWORD);
        editor.remove(KEY_FULLNAME);
        editor.remove(KEY_USERNAME);
        editor.remove(KEY_SEX);
        editor.remove(KEY_BIRTHDAY);
        editor.remove(KEY_ADDRESS);
        editor.remove(KEY_NUMBER_PHONE);
        editor.remove(KEY_EMAIL);
        editor.remove(KEY_PICTURE);
        editor.remove(KEY_IS_VALUE);
        editor.remove(KEY_SCORE_RATING);
        editor.remove(KEY_RANDOM_KEY);
        editor.remove(KEY_HAS_DATA);
        editor.clear(); // Xóa tất cả các cài đặt còn lại
        editor.apply(); // Lưu thay đổi
    }
}
