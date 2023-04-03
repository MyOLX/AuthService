package com.example.AuthService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.AuthService.models.LoginModel;
import com.example.AuthService.services.LoginService;

@RestController
@ResponseBody
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginModel loginModel) throws Exception {
        loginService.handleLogin(loginModel);
        return new ResponseEntity<>("Login successful!", HttpStatus.ACCEPTED);
    }
    
}
