package com.example.AuthService.models.login;

import com.example.AuthService.models.authorization.JwtAuthResponseModel;
import lombok.Data;

@Data
public class LoginResponseModel extends JwtAuthResponseModel {
    private String uniqueId;
}
