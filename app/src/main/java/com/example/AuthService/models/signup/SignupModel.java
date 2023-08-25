package com.example.AuthService.models.signup;

import lombok.Data;

@Data
public class SignupModel {
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String dateOfBirth;
    private String password;
    private String rePassword;
}
