package com.example.AuthService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.AuthService.models.LoginForm;
import com.example.AuthService.models.LoginResponse;
import com.example.AuthService.services.LoginServices;

@RestController
public class LoginController {

    @Autowired
    LoginServices loginServices;

    @PostMapping("/login")
    @ResponseBody
    public LoginResponse Login(@RequestBody LoginForm loginForm) throws Exception {
        return loginServices.HandleLogin(loginForm.getUsername(), loginForm.getPassword());
    }
    
}
