package com.example.AuthService.models;

import lombok.Data;

@Data
public class SignupModel {
    private String username;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private String password;
    private String re_password;
}
