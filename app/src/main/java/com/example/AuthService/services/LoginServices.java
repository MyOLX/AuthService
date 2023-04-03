package com.example.AuthService.services;

import org.springframework.stereotype.Service;

import com.example.AuthService.models.LoginResponse;
import com.example.AuthService.utils.CryptoUtil;

@Service
public class LoginServices {
    public LoginResponse HandleLogin(String username, String password) throws Exception {
        // TODO

        LoginResponse loginResponse = new LoginResponse();
        password = CryptoUtil.encodeData(username+"||||"+password);
        if( !checkIfUsernameExists(username) && validateData(username, password) )
            loginResponse.setMessageString("PASS -- > "+username+" -- "+password);
        loginResponse.setMessageString("FAIL -- > "+username+" -- "+password);

        return loginResponse;
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
