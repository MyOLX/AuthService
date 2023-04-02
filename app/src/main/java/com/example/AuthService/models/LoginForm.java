package com.example.AuthService.models;

public class LoginForm {
    // TODO Add lombok dependency to improve code
    private String username;
    private String password;
    
    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}
