package com.example.AuthService.controllers;

import com.example.AuthService.constants.AuthorizationConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AuthService.models.login.LoginModel;
import com.example.AuthService.models.login.LoginResponseModel;
import com.example.AuthService.services.LoginService;

@RestController
@RequestMapping("/api/v1/login")
@ResponseBody
@AllArgsConstructor
public class LoginController {

    @Autowired
    private LoginService loginService;

    @CrossOrigin
    @PostMapping("")
    public ResponseEntity<LoginResponseModel> login(@RequestBody LoginModel loginModel, @RequestHeader(name = AuthorizationConstants.AUTHORIZATION_TYPE_HEADER) String authType) throws Exception {
        LoginResponseModel loginResponse = new LoginResponseModel();
        try {
            authType = ( authType == null ) ? "" : authType;
            return new ResponseEntity<>(
                loginService.handleLogin(loginModel, authType),
            HttpStatus.ACCEPTED);
        } catch(Exception e) {
            loginResponse.setErrMessage(e.toString());
            return new ResponseEntity<>(loginResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping("/token/verify")
    public ResponseEntity<LoginResponseModel> verifyToken(HttpServletRequest request) {
        String authHeaderValue = request.getHeader(AuthorizationConstants.AUTHORIZATION_HEADER);
        String token = authHeaderValue.substring(AuthorizationConstants.AUTHORIZATION_PREFIX.length());
        try {
            return new ResponseEntity<>(
                loginService.handleAuthTokenVerification(token)
            , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new LoginResponseModel() {{
                setErrMessage("Unable to verify token Error: "+e);
            }}, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @CrossOrigin
    @GetMapping("/token/refresh")
    public ResponseEntity<LoginResponseModel> refreshToken(HttpServletRequest request) {
        String authHeaderValue = request.getHeader(AuthorizationConstants.AUTHORIZATION_HEADER);
        String token = authHeaderValue.substring(AuthorizationConstants.AUTHORIZATION_PREFIX.length());
        try {
            return new ResponseEntity<>(
                loginService.handleAuthTokenRefresh(token)
            , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new LoginResponseModel() {{
                setErrMessage("Error refreshing token Error: "+e);
            }}, HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }
    
}
