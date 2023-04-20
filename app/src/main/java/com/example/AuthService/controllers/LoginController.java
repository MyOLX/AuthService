package com.example.AuthService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.AuthService.models.LoginModel;
import com.example.AuthService.models.LoginResponseModel;
import com.example.AuthService.services.LoginService;

@RestController
@ResponseBody
public class LoginController {

    @Autowired
    private LoginService loginService;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<LoginResponseModel> login(@RequestBody LoginModel loginModel) throws Exception {
        LoginResponseModel loginResponse = new LoginResponseModel();
        try {
            loginService.handleLogin(loginModel);
            loginResponse.setUnique_id(loginModel.getUsername());
            return new ResponseEntity<>(loginResponse, HttpStatus.ACCEPTED);
        } catch(Exception e) {
            loginResponse.setErrMessage(e.toString());
            return new ResponseEntity<>(loginResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
