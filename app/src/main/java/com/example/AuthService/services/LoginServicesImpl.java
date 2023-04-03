package com.example.AuthService.services;

import org.springframework.stereotype.Service;

import com.example.AuthService.models.LoginModel;
import com.example.AuthService.utils.PasswordUtil;

@Service
public class LoginServicesImpl implements LoginService {
    public void handleLogin(LoginModel loginModel) throws Exception {
        
        String username = loginModel.getUsername();
        String password = PasswordUtil.getEncodedPassword(username, loginModel.getPassword());

        System.out.println("----------------------------");
        System.out.println(username+"   "+password);
        System.out.println("----------------------------");
    }
}
