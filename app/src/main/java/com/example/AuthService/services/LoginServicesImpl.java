package com.example.AuthService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AuthService.entities.AuthData;
import com.example.AuthService.models.LoginModel;
import com.example.AuthService.utils.PasswordUtil;

@Service
public class LoginServicesImpl implements LoginService {

    @Autowired
    private AuthDataRepositoryHandler repo;

    public void handleLogin(LoginModel loginModel) throws Exception {
        String username = loginModel.getUsername();
        String password = PasswordUtil.getEncodedPassword(username, loginModel.getPassword());

        AuthData authData = repo.findById(username);
        if( authData == null )
            throw new Exception("Username does not exist!");

        if( authData.getPassword() == null || authData.getPassword().equals("") )
            throw new Exception("Password cannot be null or empty!");

        if( !authData.getPassword().equals(password) ) {
            throw new Exception( "Wrong password !!" );
        }
    }
}
