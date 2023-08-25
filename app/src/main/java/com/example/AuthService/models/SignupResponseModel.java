package com.example.AuthService.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SignupResponseModel extends JwtAuthResponseModel {
    private String username;
    private String name;
    private String phone;
    private String email;
    private String dateOfBirth;
}
