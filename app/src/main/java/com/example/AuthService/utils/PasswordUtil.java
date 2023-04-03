package com.example.AuthService.utils;

public class PasswordUtil {
    public static String getEncodedPassword(String username, String password) throws Exception {
        String newStr = username+"||||"+password;
        return CryptoUtil.encodeData(newStr);
    }
}
