package com.example.AuthService.models;

import lombok.Data;

@Data
public class SignupResponseModel {
    private String username;
    private String name;
    private String phone;
    private String email;
    private String dateOfBirth;
    private String errMessage;
}
