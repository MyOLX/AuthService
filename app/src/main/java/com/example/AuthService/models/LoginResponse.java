package com.example.AuthService.models;

public class LoginResponse {
    // TODO Add lombok depency to improve
    private String messageString;
    
    public LoginResponse(String messageString) {
        this.messageString = messageString;
    }

    public String getMessage() {
        return messageString;
    }
}
