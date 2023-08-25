package com.example.AuthService.services;

import com.example.AuthService.models.login.LoginModel;
import com.example.AuthService.models.login.LoginResponseModel;

public interface LoginService {
    public LoginResponseModel handleLogin(LoginModel loginModel, String authType) throws Exception;
    public LoginResponseModel handleAuthTokenVerification(String token) throws Exception;
    public LoginResponseModel handleAuthTokenRefresh(String token) throws Exception;
}
