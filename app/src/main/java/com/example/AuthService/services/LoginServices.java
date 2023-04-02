package com.example.AuthService.services;

import org.springframework.stereotype.Service;

import com.example.AuthService.models.LoginResponse;
import com.example.AuthService.utils.CryptoUtil;

@Service
public class LoginServices {
    public LoginResponse HandleLogin(String username, String password) throws Exception {
        // TODO
        password = CryptoUtil.encodeData(username+"||||"+password);
        if( !checkIfUsernameExists(username) && validateData(username, password) )
            return new LoginResponse("PASS -- > "+username+" -- "+password);
        return new LoginResponse("FAIL -- > "+username+" -- "+password);
    }

    private boolean checkIfUsernameExists(String username) {
        // TODO
        if ( username != "" )
            return false;
        return true;
    }

    private boolean validateData(String username, String password) {
        // TODO
        if ( username != "" && password != "" )
            return true;
        return false;
    }
}
