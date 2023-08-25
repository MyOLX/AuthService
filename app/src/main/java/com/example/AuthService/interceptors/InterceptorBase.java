package com.example.AuthService.interceptors;

import com.example.AuthService.enums.AllowedUrlTestType;

public abstract class InterceptorBase {
    protected String[] whitelistedUris;
    protected String[] blacklistedUris;

    InterceptorBase(AllowedUrlTestType allowedUrlTestType, String[] urlList) {
        switch (allowedUrlTestType) {
            case BLACKLISTING_CHECK -> {
                blacklistedUris = urlList;
            }
            case WHITELISTING_CHECK -> {
                whitelistedUris = urlList;
            }
            default -> {}
        }
    }
}
