package com.example.AuthService.models;

import lombok.Data;

@Data
public class LoginResponseModel extends JwtAuthResponseModel {
    private String uniqueId;
}
