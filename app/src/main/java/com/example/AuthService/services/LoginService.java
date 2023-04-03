package com.example.AuthService.services;

import com.example.AuthService.models.LoginModel;

public interface LoginService {
    public void handleLogin(LoginModel loginModel) throws Exception;
}
