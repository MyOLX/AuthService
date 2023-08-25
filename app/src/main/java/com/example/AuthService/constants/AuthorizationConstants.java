package com.example.AuthService.constants;

public class AuthorizationConstants {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_TYPE_HEADER = "AuthorizationType";
    public static final long TOKEN_EXPIRY_TIME = 3600000; // in ms

    // Authorization types
    public static final String AUTHORIZATION_TYPE_JWT = "JWT";

}
